package com.example.gatogo.ui

/** Rutas únicas para las seis pantallas principales y la autenticación. */
object AppScreens {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val INICIO = "inicio"
    const val MAPA = "mapa"
    const val REGISTRAR = "registrar"
    const val COLECCION = "coleccion"
    const val PERFIL = "perfil"
    const val DETALLE_GATO = "detalle_gato/{idGato}"
    fun detalleGato(idGato: Int) = "detalle_gato/$idGato"
}
