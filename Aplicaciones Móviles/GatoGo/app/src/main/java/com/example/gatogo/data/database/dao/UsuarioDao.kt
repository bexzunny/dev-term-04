package com.example.gatogo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy
import com.example.gatogo.data.database.entity.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarUsuario(usuario: UsuarioEntity): Long

    @Update
    suspend fun actualizarUsuario(usuario: UsuarioEntity)

    @Delete
    suspend fun eliminarUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario WHERE id_usuario = :idUsuario LIMIT 1")
    fun obtenerUsuarioPorId(idUsuario: Int): Flow<UsuarioEntity?>

    @Query("SELECT * FROM usuario ORDER BY fecha_registro DESC")
    fun obtenerUsuarios(): Flow<List<UsuarioEntity>>

    @Query("SELECT * FROM usuario WHERE correo = :correo COLLATE NOCASE LIMIT 1")
    suspend fun buscarPorCorreo(correo: String): UsuarioEntity?

    @Query("UPDATE usuario SET nombre = :nombre WHERE id_usuario = :idUsuario")
    suspend fun actualizarNombre(idUsuario: Int, nombre: String)

    @Query("UPDATE usuario SET pass = :pass WHERE id_usuario = :idUsuario")
    suspend fun actualizarContrasena(idUsuario: Int, pass: String)

    @Query("DELETE FROM usuario WHERE id_usuario = :idUsuario")
    suspend fun eliminarPorId(idUsuario: Int)
}
