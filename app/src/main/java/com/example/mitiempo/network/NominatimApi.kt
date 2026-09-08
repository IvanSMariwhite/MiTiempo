package com.example.mitiempo.network

import com.example.mitiempo.data.model.RespuestaUbicacion
import retrofit2.http.GET
import retrofit2.http.Query

interface NominatimApi {

    @GET("reverse")
    suspend fun obtenerUbicacion(
        @Query("lat") latitud: Double,
        @Query("lon") longitud: Double,
        @Query("format") formato: String = "jsonv2",
        @Query("addressdetails") detallesDireccion: Int = 1,
        @Query("accept-language") idioma: String = "es"
    ): RespuestaUbicacion
}

