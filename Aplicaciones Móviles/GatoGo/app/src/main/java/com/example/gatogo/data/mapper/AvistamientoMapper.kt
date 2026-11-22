package com.example.gatogo.data.mapper

import com.example.gatogo.data.database.entity.AvistamientoEntity
import com.example.gatogo.domain.model.Avistamiento

fun AvistamientoEntity.toDomain() = Avistamiento(
    idAvistamiento = idAvistamiento,
    idGato = idGato,
    idUsuario = idUsuario,
    latitud = latitud,
    longitud = longitud,
    descripcion = descripcion,
    fecha = fecha
)

fun Avistamiento.toEntity() = AvistamientoEntity(
    idAvistamiento = idAvistamiento,
    idGato = idGato,
    idUsuario = idUsuario,
    latitud = latitud,
    longitud = longitud,
    descripcion = descripcion,
    fecha = fecha
)
