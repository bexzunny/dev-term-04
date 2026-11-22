package com.example.gatogo.domain.repository

import com.example.gatogo.domain.model.Gato
import kotlinx.coroutines.flow.Flow

interface GatoRepository {
    fun obtenerGatos(): Flow<List<Gato>>
    fun obtenerGatosPorUsuario(idUsuario: Int): Flow<List<Gato>>
    fun obtenerGatoPorId(idGato: Int): Flow<Gato?>
    suspend fun insertarGato(gato: Gato): Long
    suspend fun actualizarGato(gato: Gato)
    suspend fun eliminarGato(gato: Gato)
}
