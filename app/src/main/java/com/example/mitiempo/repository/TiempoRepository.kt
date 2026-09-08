package com.example.mitiempo.data.repository

import com.example.mitiempo.data.model.RespuestaTiempo
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
}