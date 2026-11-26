package com.example.gatogo.data.demo

import kotlin.random.Random

/**
 * Coordenadas ficticias para el prototipo sin servicios de ubicación.
 * La zona se mantiene fija para que los marcadores coincidan con el mapa ilustrado.
 */
data class CoordenadaDemo(
    val latitud: Double,
    val longitud: Double
)

object CoordenadasAleatorias {
    const val LATITUD_MIN = 20.24
    const val LATITUD_MAX = 20.30
    const val LONGITUD_MIN = -98.98
    const val LONGITUD_MAX = -98.90

    fun generar(random: Random = Random.Default): CoordenadaDemo =
        CoordenadaDemo(
            latitud = random.nextDouble(LATITUD_MIN, LATITUD_MAX),
            longitud = random.nextDouble(LONGITUD_MIN, LONGITUD_MAX)
        )

    fun posicionX(longitud: Double): Float =
        ((longitud - LONGITUD_MIN) / (LONGITUD_MAX - LONGITUD_MIN))
            .toFloat().coerceIn(0.04f, 0.96f)

    fun posicionY(latitud: Double): Float =
        (1.0 - (latitud - LATITUD_MIN) / (LATITUD_MAX - LATITUD_MIN))
            .toFloat().coerceIn(0.06f, 0.94f)
}
