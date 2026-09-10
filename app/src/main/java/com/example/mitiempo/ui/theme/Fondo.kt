package com.example.mitiempo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
fun Fondo(
    weatherCode: Int,
    isDay: Boolean
) {
    val context = LocalContext.current

    // Estados que comparten el mismo fondo.
    // La clave es el código recibido por Open-Meteo
    // y el valor es el código utilizado en el nombre de la imagen.
    val codigoFondo = when (weatherCode) {

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

    // Si no tenemos fondo para ese estado, no mostramos nada.
    if (codigoFondo == null) {
        return
    }

    val momentoDia = if (isDay) {
        "dia"
    } else {
        "noche"
    }

    val nombreFondo = "fondo_${codigoFondo}_${momentoDia}"

    val recursoFondo = context.resources.getIdentifier(
        nombreFondo,
        "drawable",
        context.packageName
    )

    // Si la imagen no existe en drawable, no mostramos nada.
    if (recursoFondo != 0) {
        Image(
            painter = painterResource(id = recursoFondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}