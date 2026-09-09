package com.example.mitiempo.network

import com.example.mitiempo.data.model.RespuestaTiempo
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoApi {

    @GET("v1/forecast")
    suspend fun obtenerTiempoActual(
        @Query("latitude") latitud: Double,
        @Query("longitude") longitud: Double,
        @Query("current")
        datosActuales: String =
            "temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m,is_day"
    ): RespuestaTiempo
}