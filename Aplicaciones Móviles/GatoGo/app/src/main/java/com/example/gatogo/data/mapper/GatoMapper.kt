package com.example.gatogo.data.mapper

import com.example.gatogo.data.database.entity.GatoEntity
import com.example.gatogo.domain.model.Gato

fun GatoEntity.toDomain(): Gato = Gato(
    idGato = idGato,
    nombre = nombre,
    descripcion = descripcion,
    colores = colores,
    fechaRegistro = fechaRegistro
)

fun Gato.toEntity(): GatoEntity = GatoEntity(
    idGato = idGato,
    nombre = nombre,
    descripcion = descripcion,
    colores = colores,
    fechaRegistro = fechaRegistro
)
