package com.example.mitiempo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mitiempo.data.model.RespuestaTiempo
import com.example.mitiempo.ui.theme.TiempoIcono

@Composable
fun TiempoLocal(
    tiempoActual: RespuestaTiempo,
    ciudad: String,
    provincia: String
) {

    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.45f)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TiempoIcono(
                weatherCode = tiempoActual.current.weather_code,
                isDay = tiempoActual.current.is_day == 1
            )

            Text(
                text = if (provincia.isNotEmpty()) {
                    "$ciudad, $provincia"
                } else {
                    ciudad
                }
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

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
        }
    }
}