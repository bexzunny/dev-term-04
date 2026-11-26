package com.example.gatogo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "avistamiento",
    foreignKeys = [
        ForeignKey(
            entity = GatoEntity::class,
            parentColumns = ["id_gato"],
            childColumns = ["id_gato"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = UsuarioEntity::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.NO_ACTION
        )
    ],
    indices = [Index(value = ["id_gato"]), Index(value = ["id_usuario"])]
)
data class AvistamientoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_avistamiento")
    val idAvistamiento: Int = 0,
    @ColumnInfo(name = "id_gato")
    val idGato: Int,
    @ColumnInfo(name = "id_usuario")
    val idUsuario: Int,
    @ColumnInfo(name = "latitud")
    val latitud: Double?,
    @ColumnInfo(name = "longitud")
    val longitud: Double?,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "fecha")
    val fecha: String
)
