package com.example.gatogo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.example.gatogo.data.database.entity.FotografiaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FotografiaDao {
    @Insert
    suspend fun insertar(fotografia: FotografiaEntity): Long

    @Update
    suspend fun actualizar(fotografia: FotografiaEntity)

    @Delete
    suspend fun eliminar(fotografia: FotografiaEntity)

    @Query("SELECT * FROM fotografia WHERE id_avistamiento = :idAvistamiento")
    fun porAvistamiento(idAvistamiento: Int): Flow<List<FotografiaEntity>>

    @Query("SELECT * FROM fotografia WHERE id_avistamiento = :idAvistamiento")
    suspend fun obtenerPorAvistamiento(idAvistamiento: Int): List<FotografiaEntity>
}
