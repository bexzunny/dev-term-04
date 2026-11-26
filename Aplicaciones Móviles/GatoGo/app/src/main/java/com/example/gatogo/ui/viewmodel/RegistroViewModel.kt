package com.example.gatogo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.gatogo.R
import com.example.gatogo.data.database.AppDatabase
import com.example.gatogo.data.database.entity.AvistamientoEntity
import com.example.gatogo.data.database.entity.FotografiaEntity
import com.example.gatogo.data.database.entity.GatoEntity
import com.example.gatogo.data.demo.CoordenadasAleatorias
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegistroUiState(
    val guardando: Boolean = false,
    val idGato: Int? = null,
    val errorRes: Int? = null
)

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val database: AppDatabase
) : ViewModel() {
    private val _estado = MutableStateFlow(RegistroUiState())
    val estado: StateFlow<RegistroUiState> = _estado

    fun registrar(
        idUsuario: Int,
        idGatoExistente: Int?,
        nombre: String,
        color: String,
        raza: String,
        tamano: String,
        descripcion: String,
        latitud: Double?,
        longitud: Double?,
        fotografiaUri: String?
    ) {
        if (idUsuario <= 0) {
            _estado.value = RegistroUiState(errorRes = R.string.error_sesion_requerida)
            return
        }
        if (idGatoExistente == null && nombre.isBlank() && color.isBlank() &&
            raza.isBlank() && tamano.isBlank() && descripcion.isBlank() && fotografiaUri.isNullOrBlank()
        ) {
            _estado.value = RegistroUiState(errorRes = R.string.error_datos_gato)
            return
        }
        viewModelScope.launch {
            _estado.value = RegistroUiState(guardando = true)
            try {
                val fecha = fechaActual()
                val coordenadas = if (latitud != null && longitud != null) {
                    com.example.gatogo.data.demo.CoordenadaDemo(latitud, longitud)
                } else {
                    CoordenadasAleatorias.generar()
                }
                val idGato = database.withTransaction {
                    val gatoId = idGatoExistente ?: database.gatoDao().insertarGato(
                        GatoEntity(
                            nombre = nombre.trim(),
                            descripcion = descripcion.trim(),
                            colores = color.trim(),
                            fechaRegistro = fecha,
                            raza = raza.trim(),
                            tamano = tamano.trim()
                        )
                    ).toInt()

                    val avistamientoId = database.avistamientoDao().insertarAvistamiento(
                        AvistamientoEntity(
                            idGato = gatoId,
                            idUsuario = idUsuario,
                            latitud = coordenadas.latitud,
                            longitud = coordenadas.longitud,
                            descripcion = descripcion.trim(),
                            fecha = fecha
                        )
                    ).toInt()

                    if (!fotografiaUri.isNullOrBlank()) {
                        database.fotografiaDao().insertar(
                            FotografiaEntity(
                                idAvistamiento = avistamientoId,
                                urlFotografia = fotografiaUri
                            )
                        )
                    }
                    gatoId
                }
                _estado.value = RegistroUiState(idGato = idGato)
            } catch (_: Exception) {
                _estado.value = RegistroUiState(errorRes = R.string.error_guardar_avistamiento)
            }
        }
    }

    fun limpiarEstado() {
        _estado.value = RegistroUiState()
    }

    private fun fechaActual(): String =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}
