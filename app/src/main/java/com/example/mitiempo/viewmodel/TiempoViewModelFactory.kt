package com.example.mitiempo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mitiempo.ubicacion.LocationHelper

class TiempoViewModelFactory(
    private val locationHelper: LocationHelper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(TiempoViewModel::class.java)) {
            return TiempoViewModel(
                locationHelper = locationHelper
            ) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconocido: ${modelClass.name}"
        )
    }
}
