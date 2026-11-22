package com.example.gatogo.data.mapper

import com.example.gatogo.data.database.entity.UsuarioEntity
import com.example.gatogo.domain.model.Usuario

fun UsuarioEntity.toDomain(): Usuario = Usuario(
    idUsuario = idUsuario,
    nombre = nombre,
    correo = correo,
    pass = pass,
    fechaRegistro = fechaRegistro
)

fun Usuario.toEntity(): UsuarioEntity = UsuarioEntity(
    idUsuario = idUsuario,
    nombre = nombre,
    correo = correo,
    pass = pass,
    fechaRegistro = fechaRegistro
)
