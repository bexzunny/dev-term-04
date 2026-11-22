package com.example.gatogo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy
import com.example.gatogo.data.database.entity.GatoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GatoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarGato(gato: GatoEntity): Long

    @Update
    suspend fun actualizarGato(gato: GatoEntity)

    @Delete
    suspend fun eliminarGato(gato: GatoEntity)

    @Query("SELECT * FROM gato WHERE id_gato = :idGato LIMIT 1")
    fun obtenerGatoPorId(idGato: Int): Flow<GatoEntity?>

    @Query("SELECT * FROM gato ORDER BY fecha_registro DESC")
    fun obtenerGatos(): Flow<List<GatoEntity>>

    @Query("""
        SELECT DISTINCT gato.* FROM gato
        INNER JOIN avistamiento ON avistamiento.id_gato = gato.id_gato
        WHERE avistamiento.id_usuario = :idUsuario
        ORDER BY gato.fecha_registro DESC
    """)
    fun obtenerGatosPorUsuario(idUsuario: Int): Flow<List<GatoEntity>>
}
