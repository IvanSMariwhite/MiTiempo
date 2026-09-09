package com.example.mitiempo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mitiempo.ui.theme.Fondo
import com.example.mitiempo.ui.theme.MiTiempoTheme
import com.example.mitiempo.ui.theme.TiempoIcono
import com.example.mitiempo.ubicacion.LocationHelper
import com.example.mitiempo.viewmodel.TiempoViewModel
import com.example.mitiempo.viewmodel.TiempoViewModelFactory

class MainActivity : ComponentActivity() {

    private val solicitarPermisoUbicacion =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { concedido ->

            if (concedido) {
                // Más adelante iniciaremos aquí la obtención del GPS
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            solicitarPermisoUbicacion.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }

        setContent {
            MiTiempoTheme {

                val locationHelper = LocationHelper(this)

                val tiempoViewModel: TiempoViewModel = viewModel(
                    factory = TiempoViewModelFactory(locationHelper)
                )

                WeatherScreen(
                    viewModel = tiempoViewModel
                )
            }
        }
    }
}

@Composable
fun WeatherScreen(
    viewModel: TiempoViewModel
) {
    val tiempo by viewModel.tiempoActual.collectAsState()
    val ubicacion by viewModel.ubicacionActual.collectAsState()

    val tiempoActual = tiempo

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        if (tiempoActual != null) {
            Fondo(
                weatherCode = tiempoActual.current.weather_code,
                isDay = tiempoActual.current.is_day == 1
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "MiTiempo",
                style = MaterialTheme.typography.headlineLarge
            )

            // Icono meteorológico
            if (tiempoActual != null) {
                TiempoIcono(
                    weatherCode = tiempoActual.current.weather_code,
                    isDay = tiempoActual.current.is_day == 1
                )
            }

            // Mostramos la ciudad y la provincia
            if (ubicacion != null) {

                val direccion = ubicacion!!.address

                val ciudad = direccion.city
                    ?: direccion.town
                    ?: direccion.village
                    ?: direccion.municipality
                    ?: "Ubicación desconocida"

                val provincia = direccion.province
                    ?: direccion.state_district
                    ?: ""

                Text(
                    text = if (provincia.isNotEmpty()) {
                        "$ciudad, $provincia"
                    } else {
                        ciudad
                    }
                )
            }

            if (tiempoActual != null) {

                Text(
                    text = "Temperatura: ${tiempoActual.current.temperature_2m} °C"
                )

                Text(
                    text = "Humedad: ${tiempoActual.current.relative_humidity_2m} %"
                )

                Text(
                    text = "Viento: ${tiempoActual.current.wind_speed_10m} km/h"
                )

                Text(
                    text = "Código meteorológico: ${tiempoActual.current.weather_code}"
                )

                Text(
                    text = "¿Es de día?: ${tiempoActual.current.is_day == 1}"
                )

            } else {

                Text(
                    text = "Cargando tiempo..."
                )
            }
        }
    }
}