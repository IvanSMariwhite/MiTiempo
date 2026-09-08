package com.example.mitiempo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitiempo.data.model.RespuestaTiempo
import com.example.mitiempo.data.repository.TiempoRepository
import kotlinx.coroutines.launch

class TiempoViewModel : ViewModel() {

    private val repository = TiempoRepository()

    var tiempoActual: RespuestaTiempo? = null
        private set

    fun obtenerTiempo(latitud: Double, longitud: Double) {
        viewModelScope.launch {
            tiempoActual = repository.obtenerTiempo(
                latitud,
                longitud
            )
        }
    }
}