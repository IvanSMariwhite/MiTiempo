package com.example.mitiempo.data.repository

import com.example.mitiempo.data.model.RespuestaTiempo
import com.example.mitiempo.data.model.RespuestaUbicacion
import com.example.mitiempo.network.NominatimClient
import com.example.mitiempo.network.RetrofitClient

class TiempoRepository {

    suspend fun obtenerTiempo(
        latitud: Double,
        longitud: Double
    ): RespuestaTiempo {
        return RetrofitClient.api.obtenerTiempoActual(
            latitud,
            longitud
        )
    }

    suspend fun obtenerUbicacion(
        latitud: Double,
        longitud: Double
    ): RespuestaUbicacion {
        return NominatimClient.api.obtenerUbicacion(
            latitud = latitud,
            longitud = longitud
        )
    }
}