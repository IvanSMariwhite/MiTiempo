package com.example.mitiempo.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitiempo.data.model.RespuestaTiempo
import com.example.mitiempo.data.model.RespuestaUbicacion
import com.example.mitiempo.data.repository.TiempoRepository
import com.example.mitiempo.ubicacion.LocationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TiempoViewModel(
    private val locationHelper: LocationHelper
) : ViewModel() {

    private val repository = TiempoRepository()

    private val _tiempoActual = MutableStateFlow<RespuestaTiempo?>(null)
    val tiempoActual = _tiempoActual.asStateFlow()

    private val _ubicacionActual = MutableStateFlow<RespuestaUbicacion?>(null)
    val ubicacionActual = _ubicacionActual.asStateFlow()

    init {
        Log.d("MiTiempo", "ViewModel iniciado")
        obtenerTiempo()
    }

    private fun obtenerTiempo() {

        Log.d("MiTiempo", "Obteniendo ubicación del móvil...")

        viewModelScope.launch {

            try {

                val ubicacion: Location? =
                    locationHelper.obtenerUbicacion()

                if (ubicacion != null) {

                    val latitud = ubicacion.latitude
                    val longitud = ubicacion.longitude

                    Log.d(
                        "MiTiempo",
                        "Ubicación obtenida: lat=$latitud, lon=$longitud"
                    )

                    Log.d(
                        "MiTiempo",
                        "Llamando al Repository para obtener el tiempo..."
                    )

                    val respuestaTiempo = repository.obtenerTiempo(
                        latitud = latitud,
                        longitud = longitud
                    )

                    _tiempoActual.value = respuestaTiempo

                    Log.d(
                        "MiTiempo",
                        "Respuesta del tiempo recibida: $respuestaTiempo"
                    )

                    Log.d(
                        "MiTiempo",
                        "Obteniendo ciudad y provincia..."
                    )

                    val respuestaUbicacion = repository.obtenerUbicacion(
                        latitud = latitud,
                        longitud = longitud
                    )

                    _ubicacionActual.value = respuestaUbicacion

                    Log.d(
                        "MiTiempo",
                        "Respuesta de ubicación recibida: $respuestaUbicacion"
                    )

                    Log.d(
                        "MiTiempo",
                        "Ciudad: ${respuestaUbicacion.address.city}"
                    )

                    Log.d(
                        "MiTiempo",
                        "Pueblo: ${respuestaUbicacion.address.town}"
                    )

                    Log.d(
                        "MiTiempo",
                        "Provincia: ${respuestaUbicacion.address.province}"
                    )

                    Log.d(
                        "MiTiempo",
                        "County: ${respuestaUbicacion.address.county}"
                    )

                    Log.d(
                        "MiTiempo",
                        "State district: ${respuestaUbicacion.address.state_district}"
                    )

                    Log.d(
                        "MiTiempo",
                        "Comunidad autónoma: ${respuestaUbicacion.address.state}"
                    )

                } else {

                    Log.e(
                        "MiTiempo",
                        "No se pudo obtener la ubicación"
                    )
                }

            } catch (e: Exception) {

                Log.e(
                    "MiTiempo",
                    "ERROR al obtener ubicación, tiempo o ciudad",
                    e
                )
            }
        }
    }
}
