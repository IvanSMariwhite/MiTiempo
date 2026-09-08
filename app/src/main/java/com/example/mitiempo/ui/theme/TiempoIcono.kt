package com.example.mitiempo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

@Composable
fun TiempoIcono(
    weatherCode: Int
) {
    val context = LocalContext.current

    val nombreIcono = "icono_$weatherCode"

    val recursoIcono = context.resources.getIdentifier(
        nombreIcono,
        "drawable",
        context.packageName
    )

    if (recursoIcono != 0) {
        Image(
            painter = painterResource(id = recursoIcono),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}