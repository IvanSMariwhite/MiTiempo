package com.example.mitiempo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mitiempo.ui.theme.MiTiempoTheme
import com.example.mitiempo.viewmodel.TiempoViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MiTiempoTheme {

                val tiempoViewModel: TiempoViewModel = viewModel()

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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MiTiempo",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Consulta el tiempo en cualquier lugar"
        )
    }
}
