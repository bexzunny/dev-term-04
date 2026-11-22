package com.example.gatogo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gato")
data class GatoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_gato")
    val idGato: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "colores")
    val colores: String,
    @ColumnInfo(name = "fecha_registro")
    val fechaRegistro: String
)
