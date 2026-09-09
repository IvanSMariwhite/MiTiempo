package com.example.mitiempo.data.model

data class RespuestaTiempo(
    val latitude: Double,
    val longitude: Double,
    val current: TiempoActual,
    val hourly: TiempoHorario
)

data class TiempoActual(
    val temperature_2m: Double,
    val relative_humidity_2m: Double,
    val weather_code: Int,
    val wind_speed_10m: Double,
    val is_day: Int
)

data class TiempoHorario(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val relative_humidity_2m: List<Double>,
    val weather_code: List<Int>,
    val wind_speed_10m: List<Double>,
    val is_day: List<Int>
)