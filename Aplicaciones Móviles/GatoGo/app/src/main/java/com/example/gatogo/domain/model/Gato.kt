package com.example.gatogo.domain.model

data class Gato(
    val idGato: Int = 0,
    val nombre: String,
    val descripcion: String,
    val colores: String,
    val fechaRegistro: String,
    val raza: String = "",
    val tamano: String = ""
)
