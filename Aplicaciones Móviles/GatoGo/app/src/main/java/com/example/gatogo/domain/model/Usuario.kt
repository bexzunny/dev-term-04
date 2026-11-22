package com.example.gatogo.domain.model

data class Usuario(
    val idUsuario: Int = 0,
    val nombre: String,
    val correo: String,
    val pass: String,
    val fechaRegistro: String
)
