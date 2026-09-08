package com.example.mitiempo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitiempo.data.model.RespuestaTiempo
import com.example.mitiempo.data.repository.TiempoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TiempoViewModel : ViewModel() {

    private val repository = TiempoRepository()

    private val _tiempoActual = MutableStateFlow<RespuestaTiempo?>(null)
    val tiempoActual = _tiempoActual.asStateFlow()

    init {
        Log.d("MiTiempo", "ViewModel iniciado")
        obtenerTiempo()
    }

    private fun obtenerTiempo() {

        Log.d("MiTiempo", "Iniciando petición a Open-Meteo")

        viewModelScope.launch {

            try {

                Log.d("MiTiempo", "Llamando al Repository...")

                val respuesta = repository.obtenerTiempo(
                    latitud = 39.4689,
                    longitud = -3.5347
                )

                _tiempoActual.value = respuesta

                Log.d("MiTiempo", "Respuesta recibida: $respuesta")

            } catch (e: Exception) {

                Log.e("MiTiempo", "ERROR al obtener el tiempo", e)

            }
        }
    }
}