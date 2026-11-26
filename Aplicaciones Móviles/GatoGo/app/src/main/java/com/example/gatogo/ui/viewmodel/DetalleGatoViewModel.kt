package com.example.gatogo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gatogo.data.database.dao.FotografiaDao
import com.example.gatogo.domain.repository.AvistamientoRepository
import com.example.gatogo.domain.repository.GatoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.gatogo.R
import com.example.gatogo.data.database.entity.FotografiaEntity
import com.example.gatogo.domain.model.Avistamiento
import com.example.gatogo.domain.model.Gato
import kotlinx.coroutines.flow.emptyFlow

@HiltViewModel
class DetalleGatoViewModel @Inject constructor(
    private val gatoRepository: GatoRepository,
    private val avistamientoRepository: AvistamientoRepository,
    private val fotografiaDao: FotografiaDao
) : ViewModel() {
    private val _resultado = MutableStateFlow<Int?>(null)
    val resultado: StateFlow<Int?> = _resultado

    fun limpiarResultado() {
        _resultado.value = null
    }

    fun gato(id: Int): Flow<Gato?> = gatoRepository.obtenerGatoPorId(id)
    fun avistamientos(id: Int): Flow<List<Avistamiento>> =
        avistamientoRepository.obtenerAvistamientosPorGato(id)
    fun fotografias(idAvistamiento: Int): Flow<List<FotografiaEntity>> =
        fotografiaDao.porAvistamiento(idAvistamiento)

    fun eliminar(avistamiento: Avistamiento) {
        viewModelScope.launch {
            runCatching { avistamientoRepository.eliminarAvistamiento(avistamiento) }
                .onSuccess { _resultado.value = R.string.detail_deleted }
                .onFailure { _resultado.value = R.string.error_operation }
        }
    }

    fun actualizarDescripcion(avistamiento: Avistamiento, descripcion: String) {
        viewModelScope.launch {
            runCatching {
                avistamientoRepository.actualizarAvistamiento(
                    avistamiento.copy(descripcion = descripcion.trim())
                )
            }.onSuccess { _resultado.value = R.string.detail_updated }
                .onFailure { _resultado.value = R.string.error_operation }
        }
    }

}
