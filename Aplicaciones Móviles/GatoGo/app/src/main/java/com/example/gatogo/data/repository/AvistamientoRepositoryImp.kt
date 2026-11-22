package com.example.gatogo.data.repository

import com.example.gatogo.data.database.dao.AvistamientoDao
import com.example.gatogo.data.mapper.toDomain
import com.example.gatogo.data.mapper.toEntity
import com.example.gatogo.domain.model.Avistamiento
import com.example.gatogo.domain.repository.AvistamientoRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class AvistamientoRepositoryImp @Inject constructor(
    private val dao: AvistamientoDao
) : AvistamientoRepository {
    override fun obtenerAvistamientos(): Flow<List<Avistamiento>> =
        dao.obtenerAvistamientos().map { lista -> lista.map { it.toDomain() } }

    override fun obtenerAvistamientosPorUsuario(idUsuario: Int): Flow<List<Avistamiento>> =
        dao.obtenerAvistamientosPorUsuario(idUsuario).map { lista -> lista.map { it.toDomain() } }

    override fun obtenerAvistamientosPorGato(idGato: Int): Flow<List<Avistamiento>> =
        dao.obtenerAvistamientosPorGato(idGato).map { lista -> lista.map { it.toDomain() } }

    override suspend fun insertarAvistamiento(avistamiento: Avistamiento): Long =
        dao.insertarAvistamiento(avistamiento.toEntity())

    override suspend fun actualizarAvistamiento(avistamiento: Avistamiento) =
        dao.actualizarAvistamiento(avistamiento.toEntity())

    override suspend fun eliminarAvistamiento(avistamiento: Avistamiento) =
        dao.eliminarAvistamiento(avistamiento.toEntity())
}
