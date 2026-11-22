package com.example.gatogo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gatogo.data.database.dao.AvistamientoDao
import com.example.gatogo.data.database.dao.GatoDao
import com.example.gatogo.data.database.dao.UsuarioDao
import com.example.gatogo.data.database.entity.AvistamientoEntity
import com.example.gatogo.data.database.entity.GatoEntity
import com.example.gatogo.data.database.entity.UsuarioEntity
import com.example.gatogo.data.security.PasswordHasher

@Database(
    entities = [UsuarioEntity::class, GatoEntity::class, AvistamientoEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun gatoDao(): GatoDao
    abstract fun avistamientoDao(): AvistamientoDao

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
                    val idIndex = cursor.getColumnIndexOrThrow("id_usuario")
                    val passIndex = cursor.getColumnIndexOrThrow("pass")
                    while (cursor.moveToNext()) {
                        val id = cursor.getInt(idIndex)
                        val pass = cursor.getString(passIndex)
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
    }
}
