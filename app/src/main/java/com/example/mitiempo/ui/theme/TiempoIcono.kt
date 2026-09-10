package com.example.mitiempo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TiempoIcono(
    weatherCode: Int,
    isDay: Boolean,
    tamano: Dp? = null
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    val anchoPantalla = configuration.screenWidthDp

    // Si no se indica un tamaño, usamos el tamaño grande
    // para el icono del tiempo actual.
    val tamanoIcono = tamano ?: (anchoPantalla * 0.25f)
        .coerceIn(100f, 180f)
        .dp

    // Estados que comparten el mismo icono.
    // La clave es el código recibido por Open-Meteo
    // y el valor es el código utilizado en el nombre de la imagen.
    val codigoIcono = when (weatherCode) {

        0, 1 -> 0

        2 -> 2

        3 -> 3

        45, 48 -> 45

        51, 53, 55 -> 51

        56, 57 -> 56

        61, 63, 65 -> 61

        66, 67 -> 66

        71, 73, 75, 77 -> 71

        80, 81, 82 -> 80

        85, 86 -> 85

        95, 96, 99 -> 95

        else -> null
    }

    // Si no tenemos icono para ese estado, no mostramos nada.
    if (codigoIcono == null) {
        return
    }

    val momentoDia = if (isDay) {
        "dia"
    } else {
        "noche"
    }

    val nombreIcono = "icono_${codigoIcono}_${momentoDia}"

    val recursoIcono = context.resources.getIdentifier(
        nombreIcono,
        "drawable",
        context.packageName
    )

    // Si la imagen no existe en drawable, no mostramos nada.
    if (recursoIcono != 0) {
        Image(
            painter = painterResource(id = recursoIcono),
            contentDescription = null,
            modifier = Modifier.size(tamanoIcono)
        )
    }
}