package com.example.mitiempo.data.model

data class RespuestaUbicacion(
    val address: DireccionUbicacion
)

data class DireccionUbicacion(
    val city: String? = null,
    val town: String? = null,
    val village: String? = null,
    val municipality: String? = null,
    val province: String? = null,
    val county: String? = null,
    val state_district: String? = null,
    val state: String? = null
)

