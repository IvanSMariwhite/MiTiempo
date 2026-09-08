package com.example.mitiempo.data.model

data class RespuestaTiempo(
    val latitude: Double,
    val longitude: Double,
    val current: TiempoActual
)

data class TiempoActual(
    val temperature_2m: Double,
    val weather_code: Int,
    val is_day: Int
)