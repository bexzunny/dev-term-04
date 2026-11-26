package com.example.gatogo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gatogo.data.database.dao.AvistamientoDao
import com.example.gatogo.data.database.dao.FotografiaDao
import com.example.gatogo.data.database.dao.GatoDao
import com.example.gatogo.data.database.dao.UsuarioDao
import com.example.gatogo.data.database.entity.AvistamientoEntity
import com.example.gatogo.data.database.entity.FotografiaEntity
import com.example.gatogo.data.database.entity.GatoEntity
import com.example.gatogo.data.database.entity.UsuarioEntity
import com.example.gatogo.data.security.PasswordHasher

@Database(
    entities = [
        UsuarioEntity::class,
        GatoEntity::class,
        AvistamientoEntity::class,
        FotografiaEntity::class,
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun gatoDao(): GatoDao
    abstract fun avistamientoDao(): AvistamientoDao
    abstract fun fotografiaDao(): FotografiaDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """CREATE TABLE IF NOT EXISTS `avistamiento` (
                        `id_avistamiento` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `id_gato` INTEGER NOT NULL,
                        `id_usuario` INTEGER NOT NULL,
                        `latitud` REAL NOT NULL,
                        `longitud` REAL NOT NULL,
                        `descripcion` TEXT NOT NULL,
                        `fecha` TEXT NOT NULL,
                        FOREIGN KEY(`id_gato`) REFERENCES `gato`(`id_gato`) ON UPDATE NO ACTION ON DELETE RESTRICT,
                        FOREIGN KEY(`id_usuario`) REFERENCES `usuario`(`id_usuario`) ON UPDATE NO ACTION ON DELETE RESTRICT
                    )""".trimIndent()
                )
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_avistamiento_id_gato` ON `avistamiento` (`id_gato`)")
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_avistamiento_id_usuario` ON `avistamiento` (`id_usuario`)")

                val pendientes = mutableListOf<Pair<Int, String>>()
                db.query("SELECT `id_usuario`, `pass` FROM `usuario`").use { cursor ->
                    while (cursor.moveToNext()) {
                        val id = cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"))
                        val pass = cursor.getString(cursor.getColumnIndexOrThrow("pass"))
                        if (!PasswordHasher.isHashed(pass)) pendientes += id to pass
                    }
                }
                val update = db.compileStatement(
                    "UPDATE `usuario` SET `pass` = ? WHERE `id_usuario` = ?"
                )
                pendientes.forEach { (id, plainPassword) ->
                    update.clearBindings()
                    update.bindString(1, PasswordHasher.hash(plainPassword))
                    update.bindLong(2, id.toLong())
                    update.executeUpdateDelete()
                }
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Replace the legacy `colores` column with the agreed `color` column.
                db.execSQL(
                    """CREATE TABLE `gato_nuevo` (
                        `id_gato` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `nombre` TEXT NOT NULL,
                        `descripcion` TEXT NOT NULL,
                        `color` TEXT NOT NULL,
                        `fecha_registro` TEXT NOT NULL,
                        `raza` TEXT NOT NULL DEFAULT '',
                        `tamano` TEXT NOT NULL DEFAULT ''
                    )""".trimIndent()
                )
                db.execSQL(
                    """INSERT INTO `gato_nuevo`
                        (`id_gato`, `nombre`, `descripcion`, `color`, `fecha_registro`, `raza`, `tamano`)
                        SELECT `id_gato`, `nombre`, `descripcion`, `colores`, `fecha_registro`, '', ''
                        FROM `gato`""".trimIndent()
                )

                // Coordinates are nullable so a sighting can be saved without location permission.
                db.execSQL(
                    """CREATE TABLE `avistamiento_nuevo` (
                        `id_avistamiento` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `id_gato` INTEGER NOT NULL,
                        `id_usuario` INTEGER NOT NULL,
                        `latitud` REAL,
                        `longitud` REAL,
                        `descripcion` TEXT NOT NULL,
                        `fecha` TEXT NOT NULL,
                        FOREIGN KEY(`id_gato`) REFERENCES `gato_nuevo`(`id_gato`) ON UPDATE NO ACTION ON DELETE RESTRICT,
                        FOREIGN KEY(`id_usuario`) REFERENCES `usuario`(`id_usuario`) ON UPDATE NO ACTION ON DELETE RESTRICT
                    )""".trimIndent()
                )
                db.execSQL(
                    """INSERT INTO `avistamiento_nuevo`
                        (`id_avistamiento`, `id_gato`, `id_usuario`, `latitud`, `longitud`, `descripcion`, `fecha`)
                        SELECT `id_avistamiento`, `id_gato`, `id_usuario`, `latitud`, `longitud`, `descripcion`, `fecha`
                        FROM `avistamiento`""".trimIndent()
                )

                // Drop the child table first; the replacement sighting table points at gato_nuevo.
                db.execSQL("DROP TABLE `avistamiento`")
                db.execSQL("DROP TABLE `gato`")
                db.execSQL("ALTER TABLE `gato_nuevo` RENAME TO `gato`")
                db.execSQL("ALTER TABLE `avistamiento_nuevo` RENAME TO `avistamiento`")
                db.execSQL("CREATE INDEX `index_avistamiento_id_gato` ON `avistamiento` (`id_gato`)")
                db.execSQL("CREATE INDEX `index_avistamiento_id_usuario` ON `avistamiento` (`id_usuario`)")

                db.execSQL(
                    """CREATE TABLE `fotografia` (
                        `id_fotografia` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `id_avistamiento` INTEGER NOT NULL,
                        `url_fotografia` TEXT NOT NULL,
                        FOREIGN KEY(`id_avistamiento`) REFERENCES `avistamiento`(`id_avistamiento`) ON UPDATE NO ACTION ON DELETE CASCADE
                    )""".trimIndent()
                )
                db.execSQL("CREATE INDEX `index_fotografia_id_avistamiento` ON `fotografia` (`id_avistamiento`)")

                db.execSQL(
                    """CREATE TABLE `reporte` (
                        `id_reporte` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `id_avistamiento` INTEGER NOT NULL,
                        `id_usuario` INTEGER NOT NULL,
                        `tipo` TEXT NOT NULL,
                        `descripcion` TEXT NOT NULL,
                        `fecha` TEXT NOT NULL,
                        `estado` TEXT NOT NULL DEFAULT 'pendiente',
                        FOREIGN KEY(`id_avistamiento`) REFERENCES `avistamiento`(`id_avistamiento`) ON UPDATE NO ACTION ON DELETE CASCADE,
                        FOREIGN KEY(`id_usuario`) REFERENCES `usuario`(`id_usuario`) ON UPDATE NO ACTION ON DELETE CASCADE
                    )""".trimIndent()
                )
                db.execSQL("CREATE INDEX `index_reporte_id_avistamiento` ON `reporte` (`id_avistamiento`)")
                db.execSQL("CREATE INDEX `index_reporte_id_usuario` ON `reporte` (`id_usuario`)")

                db.execSQL(
                    """CREATE TABLE `preferencia_usuario` (
                        `id_preferencia` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `id_usuario` INTEGER NOT NULL,
                        `ubicacion_aproximada` INTEGER NOT NULL DEFAULT 1,
                        `notificaciones_activas` INTEGER NOT NULL DEFAULT 1,
                        FOREIGN KEY(`id_usuario`) REFERENCES `usuario`(`id_usuario`) ON UPDATE NO ACTION ON DELETE CASCADE
                    )""".trimIndent()
                )
                db.execSQL("CREATE UNIQUE INDEX `index_preferencia_usuario_id_usuario` ON `preferencia_usuario` (`id_usuario`)")
            }
        }

        // Elimina las tablas descartadas conservando usuarios, gatos, avistamientos y fotografías.
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DROP TABLE IF EXISTS `reporte`")
                db.execSQL("DROP TABLE IF EXISTS `preferencia_usuario`")
            }
        }
    }
}
