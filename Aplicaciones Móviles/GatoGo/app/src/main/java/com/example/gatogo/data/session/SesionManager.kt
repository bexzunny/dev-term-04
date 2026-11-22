package com.example.gatogo.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.gatoGoDataStore by preferencesDataStore(name = "gatogo_sesion")

@Singleton
class SesionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val dataStore = context.applicationContext.gatoGoDataStore
    private val userIdKey = intPreferencesKey("id_usuario_activo")

    val usuarioId: Flow<Int?> = dataStore.data
        .catch { error ->
            if (error is IOException) emit(androidx.datastore.preferences.core.emptyPreferences())
            else throw error
        }
        .map { preferences -> preferences[userIdKey] }

    suspend fun guardarSesion(idUsuario: Int) {
        dataStore.edit { preferences -> preferences[userIdKey] = idUsuario }
    }

    suspend fun cerrarSesion() {
        dataStore.edit { preferences -> preferences.remove(userIdKey) }
    }
}
