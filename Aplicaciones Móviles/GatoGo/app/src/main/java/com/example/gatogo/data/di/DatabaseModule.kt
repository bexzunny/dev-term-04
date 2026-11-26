package com.example.gatogo.data.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gatogo.data.database.AppDatabase
import com.example.gatogo.data.database.dao.AvistamientoDao
import com.example.gatogo.data.database.dao.FotografiaDao
import com.example.gatogo.data.database.dao.GatoDao
import com.example.gatogo.data.database.dao.UsuarioDao
import com.example.gatogo.data.demo.CoordenadasAleatorias
import com.example.gatogo.data.security.PasswordHasher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "gatogo.db"
    )
        .addMigrations(AppDatabase.MIGRATION_1_2, AppDatabase.MIGRATION_2_3, AppDatabase.MIGRATION_3_4)
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                insertarDatosDePrueba(db, context)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                val hayGatos = db.query("SELECT COUNT(*) FROM gato").use { cursor ->
                    cursor.moveToFirst() && cursor.getInt(0) > 0
                }
                if (!hayGatos) insertarDatosDePrueba(db, context)
            }
        })
        .build()

    private fun insertarDatosDePrueba(db: SupportSQLiteDatabase, context: Context) {
        val usuarios = listOf(
            "demo@gatogo.local" to "Usuario Demo",
            "ana@gatogo.local" to "Ana Michi",
            "luis@gatogo.local" to "Luis Gato"
        )
        val idsUsuarios = usuarios.map { (correo, nombre) ->
            var idUsuario: Int? = db.query(
                "SELECT id_usuario FROM usuario WHERE correo = ? LIMIT 1",
                arrayOf(correo)
            ).use { cursor ->
                if (cursor.moveToFirst()) cursor.getInt(0) else null
            }

            if (idUsuario == null) {
                db.execSQL(
                    "INSERT INTO usuario(nombre, correo, pass, fecha_registro) VALUES(?, ?, ?, ?)",
                    arrayOf(nombre, correo, PasswordHasher.hash("GatoGO123"), "2026-11-20 10:00:00")
                )
                idUsuario = db.query(
                    "SELECT id_usuario FROM usuario WHERE correo = ? LIMIT 1",
                    arrayOf(correo)
                ).use { cursor ->
                    if (cursor.moveToFirst()) cursor.getInt(0) else null
                }
            }
            requireNotNull(idUsuario)
        }

        data class GatoPrueba(
            val nombre: String,
            val color: String,
            val raza: String,
            val tamano: String,
            val descripcion: String,
            val imagen: String
        )

        val gatos = listOf(
            GatoPrueba("Milo", "Naranja", "Mestizo", "Mediano", "Gato tranquilo, sin collar.", "gato_demo_1"),
            GatoPrueba("Luna", "Gris", "Mestiza", "Pequeño", "Tiene ojos claros y suele descansar en el parque.", "gato_demo_2"),
            GatoPrueba("Café", "Negro", "Mestizo", "Grande", "Pelaje oscuro y una pequeña mancha en el pecho.", "gato_demo_3"),
            GatoPrueba("Nube", "Blanco", "Mestiza", "Mediano", "Gatita blanca que se acerca a las personas.", "gato_demo_4"),
            GatoPrueba("Chispa", "Atigrado", "Mestizo", "Pequeño", "Muy curioso; se observó cerca de una jardinera.", "gato_demo_1"),
            GatoPrueba("Manchas", "Blanco y negro", "Mestizo", "Mediano", "Tiene manchas oscuras en el lomo.", "gato_demo_2")
        )

        val idsGatos = gatos.map { gato ->
            db.insert(
                "gato",
                SQLiteDatabase.CONFLICT_ABORT,
                ContentValues().apply {
                    put("nombre", gato.nombre)
                    put("descripcion", gato.descripcion)
                    put("color", gato.color)
                    put("fecha_registro", "2026-11-20 10:00:00")
                    put("raza", gato.raza)
                    put("tamano", gato.tamano)
                }
            ).toInt()
        }

        // Varios gatos tienen más de un avistamiento, para demostrar la relación 1:N.
        val registros = listOf(
            Triple(0, 0, "Lo vi caminando junto a la plaza."),
            Triple(1, 0, "Estaba descansando bajo un árbol."),
            Triple(2, 0, "Se encontraba cerca de unas bancas."),
            Triple(3, 0, "Avistamiento junto a una jardinera."),
            Triple(2, 1, "Segundo avistamiento de Café, en otra fecha."),
            Triple(4, 1, "Se acercó a la zona de comida."),
            Triple(3, 2, "Nuevo avistamiento de Nube."),
            Triple(5, 2, "Estaba jugando entre las plantas.")
        )

        registros.forEachIndexed { index, registro ->
            val (indiceGato, indiceUsuario, descripcion) = registro
            val coordenada = CoordenadasAleatorias.generar()
            val idAvistamiento = db.insert(
                "avistamiento",
                SQLiteDatabase.CONFLICT_ABORT,
                ContentValues().apply {
                    put("id_gato", idsGatos[indiceGato])
                    put("id_usuario", idsUsuarios[indiceUsuario])
                    put("latitud", coordenada.latitud)
                    put("longitud", coordenada.longitud)
                    put("descripcion", descripcion)
                    put("fecha", "2026-11-${20 - index} 12:00:00")
                }
            ).toInt()

            val resourceUri = "android.resource://${context.packageName}/drawable/${gatos[indiceGato].imagen}"
            db.insert(
                "fotografia",
                SQLiteDatabase.CONFLICT_ABORT,
                ContentValues().apply {
                    put("id_avistamiento", idAvistamiento)
                    put("url_fotografia", resourceUri)
                }
            )
        }
    }

    @Provides
    fun provideUsuarioDao(database: AppDatabase): UsuarioDao = database.usuarioDao()

    @Provides
    fun provideGatoDao(database: AppDatabase): GatoDao = database.gatoDao()

    @Provides
    fun provideAvistamientoDao(database: AppDatabase): AvistamientoDao = database.avistamientoDao()

    @Provides
    fun provideFotografiaDao(database: AppDatabase): FotografiaDao = database.fotografiaDao()
}
