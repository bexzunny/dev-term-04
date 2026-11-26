package com.example.gatogo.domain.model

data class Avistamiento(
    val idAvistamiento: Int = 0,
    val idGato: Int,
    val idUsuario: Int,
    val latitud: Double?,
    val longitud: Double?,
    val descripcion: String,
    val fecha: String
)
