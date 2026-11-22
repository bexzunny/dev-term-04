package com.example.gatogo.domain.repository

import com.example.gatogo.domain.model.Avistamiento
import kotlinx.coroutines.flow.Flow

interface AvistamientoRepository {
    fun obtenerAvistamientos(): Flow<List<Avistamiento>>
    fun obtenerAvistamientosPorUsuario(idUsuario: Int): Flow<List<Avistamiento>>
    fun obtenerAvistamientosPorGato(idGato: Int): Flow<List<Avistamiento>>
    suspend fun insertarAvistamiento(avistamiento: Avistamiento): Long
    suspend fun actualizarAvistamiento(avistamiento: Avistamiento)
    suspend fun eliminarAvistamiento(avistamiento: Avistamiento)
}
