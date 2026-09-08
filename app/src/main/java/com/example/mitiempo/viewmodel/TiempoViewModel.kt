package com.example.mitiempo.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitiempo.data.model.RespuestaTiempo
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

                    Log.d(
                        "MiTiempo",
                        "Ubicación obtenida: " +
                                "lat=${ubicacion.latitude}, " +
                                "lon=${ubicacion.longitude}"
                    )

                    Log.d(
                        "MiTiempo",
                        "Llamando al Repository..."
                    )

                    val respuesta = repository.obtenerTiempo(
                        latitud = ubicacion.latitude,
                        longitud = ubicacion.longitude
                    )

                    _tiempoActual.value = respuesta

                    Log.d(
                        "MiTiempo",
                        "Respuesta recibida: $respuesta"
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
                    "ERROR al obtener ubicación o tiempo",
                    e
                )
            }
        }
    }
}