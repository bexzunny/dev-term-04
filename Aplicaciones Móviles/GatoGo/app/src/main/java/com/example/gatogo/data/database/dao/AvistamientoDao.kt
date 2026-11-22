package com.example.gatogo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gatogo.data.database.entity.AvistamientoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AvistamientoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarAvistamiento(avistamiento: AvistamientoEntity): Long

    @Update
    suspend fun actualizarAvistamiento(avistamiento: AvistamientoEntity)

    @Delete
    suspend fun eliminarAvistamiento(avistamiento: AvistamientoEntity)

    @Query("SELECT * FROM avistamiento ORDER BY fecha DESC")
    fun obtenerAvistamientos(): Flow<List<AvistamientoEntity>>

    @Query("SELECT * FROM avistamiento WHERE id_usuario = :idUsuario ORDER BY fecha DESC")
    fun obtenerAvistamientosPorUsuario(idUsuario: Int): Flow<List<AvistamientoEntity>>

    @Query("SELECT * FROM avistamiento WHERE id_gato = :idGato ORDER BY fecha DESC")
    fun obtenerAvistamientosPorGato(idGato: Int): Flow<List<AvistamientoEntity>>
}
