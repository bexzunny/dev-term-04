package com.example.gatogo.data.repository

import com.example.gatogo.data.database.dao.GatoDao
import com.example.gatogo.data.mapper.toDomain
import com.example.gatogo.data.mapper.toEntity
import com.example.gatogo.domain.model.Gato
import com.example.gatogo.domain.repository.GatoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GatoRepositoryImp @Inject constructor(
    private val gatoDao: GatoDao
) : GatoRepository {
    override fun obtenerGatos(): Flow<List<Gato>> =
        gatoDao.obtenerGatos().map { lista -> lista.map { it.toDomain() } }

    override fun obtenerGatosPorUsuario(idUsuario: Int): Flow<List<Gato>> =
        gatoDao.obtenerGatosPorUsuario(idUsuario).map { lista -> lista.map { it.toDomain() } }

    override fun obtenerGatoPorId(idGato: Int): Flow<Gato?> =
        gatoDao.obtenerGatoPorId(idGato).map { it?.toDomain() }

    override suspend fun insertarGato(gato: Gato): Long =
        gatoDao.insertarGato(gato.toEntity())

    override suspend fun actualizarGato(gato: Gato) =
        gatoDao.actualizarGato(gato.toEntity())

    override suspend fun eliminarGato(gato: Gato) =
        gatoDao.eliminarGato(gato.toEntity())
}
