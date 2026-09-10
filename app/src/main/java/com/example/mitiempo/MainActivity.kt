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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mitiempo.ui.components.TablaPrevisionLocal
import com.example.mitiempo.ui.components.TiempoLocal
import com.example.mitiempo.ui.theme.Fondo
import com.example.mitiempo.ui.theme.MiTiempoTheme
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Título provisional. Más adelante será sustituido por el logo.
            Text(
                text = "MiTiempo",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 24.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (tiempoActual != null) {

                val direccion = ubicacion?.address

                val ciudad = direccion?.let {
                    it.city
                        ?: it.town
                        ?: it.village
                        ?: it.municipality
                        ?: "Ubicación desconocida"
                } ?: "Ubicación desconocida"

                val provincia = direccion?.let {
                    it.province
                        ?: it.state_district
                        ?: ""
                } ?: ""

                TiempoLocal(
                    tiempoActual = tiempoActual,
                    ciudad = ciudad,
                    provincia = provincia
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                TablaPrevisionLocal(
                    tiempoActual = tiempoActual
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

            } else {

                Text(
                    text = "Cargando tiempo..."
                )
            }
        }
    }
}