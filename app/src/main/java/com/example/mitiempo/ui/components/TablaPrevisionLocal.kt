package com.example.mitiempo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.mitiempo.data.model.RespuestaTiempo
import com.example.mitiempo.ui.theme.TiempoIcono
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TablaPrevisionLocal(
    tiempoActual: RespuestaTiempo
) {

    val configuration = LocalConfiguration.current

    // Cada día ocupa aproximadamente un tercio de la pantalla.
    // El tamaño del icono se adapta al tamaño disponible.
    val tamanoIconoPrevision = (configuration.screenWidthDp * 0.08f)
        .coerceIn(40f, 70f)
        .dp

    val horas = listOf(8, 14, 20)

    val nombresDias = listOf(
        "HOY",
        "MAÑANA",
        obtenerNombreDia(
            tiempoActual.hourly.time[48]
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Previsión",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            for (dia in 0..2) {

                Card(
                    modifier = Modifier.weight(1f)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = nombresDias[dia],
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        for (hora in horas) {

                            val indice = dia * 24 + hora

                            if (
                                indice < tiempoActual.hourly.time.size
                            ) {

                                PrevisionHoraLocal(
                                    hora = tiempoActual.hourly.time[indice],
                                    temperatura =
                                        tiempoActual.hourly.temperature_2m[indice],
                                    humedad =
                                        tiempoActual.hourly.relative_humidity_2m[indice],
                                    viento =
                                        tiempoActual.hourly.wind_speed_10m[indice],
                                    weatherCode =
                                        tiempoActual.hourly.weather_code[indice],
                                    isDay =
                                        tiempoActual.hourly.is_day[indice] == 1,
                                    tamanoIcono = tamanoIconoPrevision
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PrevisionHoraLocal(
    hora: String,
    temperatura: Double,
    humedad: Double,
    viento: Double,
    weatherCode: Int,
    isDay: Boolean,
    tamanoIcono: androidx.compose.ui.unit.Dp
) {

    val horaMostrada = hora.substringAfter("T")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = horaMostrada,
            style = MaterialTheme.typography.labelLarge
        )

        TiempoIcono(
            weatherCode = weatherCode,
            isDay = isDay,
            tamano = tamanoIcono
        )

        Text(
            text = "${temperatura.toInt()} °C",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Humedad: ${humedad.toInt()}%"
        )

        Text(
            text = "Viento: ${viento.toInt()} km/h"
        )
    }
}

private fun obtenerNombreDia(
    fechaHora: String
): String {

    val fecha = LocalDate.parse(
        fechaHora.substringBefore("T")
    )

    return fecha.dayOfWeek
        .getDisplayName(
            TextStyle.FULL,
            Locale("es", "ES")
        )
        .uppercase(Locale("es", "ES"))
}