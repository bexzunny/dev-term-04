package com.example.gatogo.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatogo.data.repository.ContrasenaNuevaCortaException
import com.example.gatogo.data.repository.CredencialesIncorrectasException
import com.example.gatogo.data.repository.CorreoYaRegistradoException
import com.example.gatogo.data.session.SesionManager
import com.example.gatogo.domain.model.Usuario
import com.example.gatogo.domain.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SesionUiState(
    val cargada: Boolean = false,
    val idUsuario: Int? = null
)

data class AccesoUiState(
    val cargando: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val repository: UsuarioRepository,
    private val sesionManager: SesionManager
) : ViewModel() {

    val usuarios: StateFlow<List<Usuario>> = repository.obtenerUsuarios().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val sesion: StateFlow<SesionUiState> = sesionManager.usuarioId
        .map { id -> SesionUiState(cargada = true, idUsuario = id) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SesionUiState())

    private val _acceso = kotlinx.coroutines.flow.MutableStateFlow(AccesoUiState())
    val acceso: StateFlow<AccesoUiState> = _acceso

    fun iniciarSesionOCrearCuenta(correo: String, password: String) {
        val correoLimpio = correo.trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(correoLimpio).matches()) {
            _acceso.value = AccesoUiState(error = "Introduce un correo electrónico válido.")
            return
        }
        if (password.isBlank()) {
            _acceso.value = AccesoUiState(error = "Escribe tu contraseña.")
            return
        }

        viewModelScope.launch {
            _acceso.value = AccesoUiState(cargando = true)
            try {
                val usuario = repository.autenticarOCrear(correoLimpio, password)
                sesionManager.guardarSesion(usuario.idUsuario)
                _acceso.value = AccesoUiState()
            } catch (error: Exception) {
                val mensaje = when (error) {
                    is CredencialesIncorrectasException -> error.message
                    is ContrasenaNuevaCortaException -> error.message
                    is CorreoYaRegistradoException -> error.message
                    else -> "No fue posible acceder. Revisa los datos e inténtalo nuevamente."
                }
                _acceso.value = AccesoUiState(error = mensaje)
            }
        }
    }

    fun limpiarErrorAcceso() {
        _acceso.value = AccesoUiState()
    }

    fun cerrarSesion() {
        viewModelScope.launch { sesionManager.cerrarSesion() }
    }

    fun obtenerUsuarioPorId(idUsuario: Int) = repository.obtenerUsuarioPorId(idUsuario)

    /** Se conserva para compatibilidad con las operaciones CRUD previas. */
    fun agregarUsuario(nombre: String, correo: String, pass: String) {
        val nombreLimpio = nombre.trim()
        val correoLimpio = correo.trim()
        if (nombreLimpio.isEmpty() || correoLimpio.isEmpty() || pass.isBlank()) return
        viewModelScope.launch {
            runCatching {
                repository.insertarUsuario(
                    Usuario(
                        nombre = nombreLimpio,
                        correo = correoLimpio,
                        pass = pass,
                        fechaRegistro = fechaActual()
                    )
                )
            }
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch { repository.actualizarUsuario(usuario) }
    }

    fun eliminarUsuario(usuario: Usuario) {
        viewModelScope.launch { repository.eliminarUsuario(usuario) }
    }

    private fun fechaActual(): String =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}
