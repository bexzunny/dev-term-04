package com.example.gatogo.domain.repository

import com.example.gatogo.domain.model.Usuario
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    fun obtenerUsuarios(): Flow<List<Usuario>>
    fun obtenerUsuarioPorId(idUsuario: Int): Flow<Usuario?>
    suspend fun buscarPorCorreo(correo: String): Usuario?
    suspend fun autenticarOCrear(correo: String, password: String): Usuario
    suspend fun insertarUsuario(usuario: Usuario): Long
    suspend fun actualizarUsuario(usuario: Usuario)
    suspend fun eliminarUsuario(usuario: Usuario)
}
