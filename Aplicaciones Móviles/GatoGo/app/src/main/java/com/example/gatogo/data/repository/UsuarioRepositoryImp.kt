package com.example.gatogo.data.repository

import androidx.room.withTransaction
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.gatogo.R
import com.example.gatogo.data.database.AppDatabase
import com.example.gatogo.data.database.dao.UsuarioDao
import com.example.gatogo.data.database.entity.UsuarioEntity
import com.example.gatogo.data.mapper.toDomain
import com.example.gatogo.data.mapper.toEntity
import com.example.gatogo.data.security.PasswordHasher
import com.example.gatogo.domain.model.Usuario
import com.example.gatogo.domain.repository.UsuarioRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CredencialesIncorrectasException : Exception("El correo o la contraseña no son correctos.")
class ContrasenaNuevaCortaException : Exception("Para crear una cuenta nueva, la contraseña debe tener al menos 8 caracteres.")
class CorreoYaRegistradoException : Exception("Ya existe una cuenta con ese correo.")

@Singleton
class UsuarioRepositoryImp @Inject constructor(
    private val usuarioDao: UsuarioDao,
    private val database: AppDatabase,
    @ApplicationContext private val context: Context
) : UsuarioRepository {

    override fun obtenerUsuarios(): Flow<List<Usuario>> =
        usuarioDao.obtenerUsuarios().map { lista -> lista.map { it.toDomain() } }

    override fun obtenerUsuarioPorId(idUsuario: Int): Flow<Usuario?> =
        usuarioDao.obtenerUsuarioPorId(idUsuario).map { it?.toDomain() }

    override suspend fun buscarPorCorreo(correo: String): Usuario? =
        usuarioDao.buscarPorCorreo(correo.trim())?.toDomain()

    override suspend fun insertarUsuario(usuario: Usuario): Long {
        val correo = usuario.correo.trim().lowercase(Locale.ROOT)
        val passwordHash = if (PasswordHasher.isHashed(usuario.pass)) {
            usuario.pass
        } else {
            withContext(Dispatchers.Default) { PasswordHasher.hash(usuario.pass) }
        }
        return database.withTransaction {
            if (usuarioDao.buscarPorCorreo(correo) != null) {
                throw CorreoYaRegistradoException()
            }
            usuarioDao.insertarUsuario(
                usuario.copy(correo = correo, pass = passwordHash).toEntity()
            )
        }
    }

    override suspend fun autenticarOCrear(correo: String, password: String): Usuario {
        val correoNormalizado = correo.trim().lowercase(Locale.ROOT)
        val usuarioExistente = usuarioDao.buscarPorCorreo(correoNormalizado)

        if (usuarioExistente != null) {
            return validarUsuarioExistente(usuarioExistente, password)
        }

        if (password.length < 8) throw ContrasenaNuevaCortaException()

        val passwordHash = withContext(Dispatchers.Default) { PasswordHasher.hash(password) }
        val nombreInicial = correoNormalizado.substringBefore('@')
            .replace('.', ' ').replace('_', ' ').replace('-', ' ')
            .split(' ').filter { it.isNotBlank() }
            .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
            .ifBlank { context.getString(R.string.default_user_name) }

        val nuevoUsuario = UsuarioEntity(
            nombre = nombreInicial,
            correo = correoNormalizado,
            pass = passwordHash,
            fechaRegistro = fechaActual()
        )

        // Revisa e inserta en una misma transacción para impedir altas duplicadas simultáneas.
        val insertado = database.withTransaction {
            if (usuarioDao.buscarPorCorreo(correoNormalizado) == null) {
                val id = usuarioDao.insertarUsuario(nuevoUsuario)
                nuevoUsuario.copy(idUsuario = id.toInt())
            } else null
        }

        if (insertado != null) return insertado.toDomain()

        // Otra operación pudo crear la cuenta mientras se calculaba el hash.
        val creadoEnParalelo = usuarioDao.buscarPorCorreo(correoNormalizado)
            ?: throw IllegalStateException("No fue posible recuperar la cuenta creada.")
        return validarUsuarioExistente(creadoEnParalelo, password)
    }

    private suspend fun validarUsuarioExistente(
        usuario: UsuarioEntity,
        password: String
    ): Usuario {
        val esValida = withContext(Dispatchers.Default) {
            if (PasswordHasher.isHashed(usuario.pass)) {
                PasswordHasher.verify(password, usuario.pass)
            } else {
                PasswordHasher.matchesLegacyPlainText(password, usuario.pass)
            }
        }
        if (!esValida) throw CredencialesIncorrectasException()

        // Las cuentas de la versión anterior se actualizan al iniciar sesión correctamente.
        val passSeguro = if (PasswordHasher.isHashed(usuario.pass)) {
            usuario.pass
        } else {
            withContext(Dispatchers.Default) { PasswordHasher.hash(password) }
                .also { hash -> usuarioDao.actualizarUsuario(usuario.copy(pass = hash)) }
        }
        return usuario.copy(pass = passSeguro).toDomain()
    }

    override suspend fun actualizarUsuario(usuario: Usuario) =
        usuarioDao.actualizarUsuario(usuario.toEntity())

    override suspend fun eliminarUsuario(usuario: Usuario) =
        usuarioDao.eliminarUsuario(usuario.toEntity())

    override suspend fun actualizarNombre(idUsuario: Int, nombre: String) {
        usuarioDao.actualizarNombre(idUsuario, nombre.trim())
    }

    override suspend fun actualizarContrasena(idUsuario: Int, nuevaContrasena: String) {
        require(nuevaContrasena.length >= 8) { "La contraseña debe tener al menos 8 caracteres." }
        val hash = withContext(Dispatchers.Default) { PasswordHasher.hash(nuevaContrasena) }
        usuarioDao.actualizarContrasena(idUsuario, hash)
    }

    override suspend fun eliminarCuentaLocal(idUsuario: Int) {
        database.withTransaction {
            // Las fotografías se eliminan por FK CASCADE al borrar los avistamientos.
            database.avistamientoDao().eliminarPorUsuario(idUsuario)
            usuarioDao.eliminarPorId(idUsuario)
        }
    }

    private fun fechaActual(): String =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}
