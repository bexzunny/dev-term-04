package com.example.gatogo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val idUsuario: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "correo")
    val correo: String,
    @ColumnInfo(name = "pass")
    val pass: String,
    @ColumnInfo(name = "fecha_registro")
    val fechaRegistro: String
)
