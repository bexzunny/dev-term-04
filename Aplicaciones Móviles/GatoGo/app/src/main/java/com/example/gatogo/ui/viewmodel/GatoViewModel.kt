package com.example.gatogo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatogo.R
import com.example.gatogo.domain.model.Gato
import com.example.gatogo.domain.repository.GatoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class RegistroGatoUiState(
    val guardando: Boolean = false,
    val idCreado: Long? = null,
    val errorRes: Int? = null
)

@HiltViewModel
class GatoViewModel @Inject constructor(
    private val repository: GatoRepository
) : ViewModel() {
    val gatos: StateFlow<List<Gato>> = repository.obtenerGatos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private val _registroEstado = MutableStateFlow(RegistroGatoUiState())
    val registroEstado: StateFlow<RegistroGatoUiState> = _registroEstado

    fun obtenerGatosPorUsuario(idUsuario: Int) = repository.obtenerGatosPorUsuario(idUsuario)

    fun agregarGato(nombre: String, descripcion: String, colores: String) {
        val nombreLimpio = nombre.trim()
        if (nombreLimpio.isEmpty()) {
            _registroEstado.value = RegistroGatoUiState(errorRes = R.string.error_cat_name_required)
            return
        }

        viewModelScope.launch {
            _registroEstado.value = RegistroGatoUiState(guardando = true)
            try {
                val id = repository.insertarGato(
                    Gato(
                        nombre = nombreLimpio,
                        descripcion = descripcion.trim(),
                        colores = colores.trim(),
                        fechaRegistro = fechaActual()
                    )
                )
                _registroEstado.value = RegistroGatoUiState(idCreado = id)
            } catch (error: Exception) {
                _registroEstado.value = RegistroGatoUiState(
                    errorRes = R.string.error_save_cat
                )
            }
        }
    }

    fun limpiarEstadoRegistro() {
        _registroEstado.value = RegistroGatoUiState()
    }

    fun actualizarGato(gato: Gato) {
        viewModelScope.launch { repository.actualizarGato(gato) }
    }

    fun eliminarGato(gato: Gato) {
        viewModelScope.launch { repository.eliminarGato(gato) }
    }

    private fun fechaActual(): String =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}
