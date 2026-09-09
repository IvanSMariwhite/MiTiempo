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

    val momentoDia = if (isDay) {
        "dia"
    } else {
        "noche"
    }

    val nombreIcono = "icono_${weatherCode}_${momentoDia}"

    val recursoIcono = context.resources.getIdentifier(
        nombreIcono,
        "drawable",
        context.packageName
    )

    if (recursoIcono != 0) {
        Image(
            painter = painterResource(id = recursoIcono),
            contentDescription = null,
            modifier = Modifier.size(tamanoIcono)
        )
    }
}