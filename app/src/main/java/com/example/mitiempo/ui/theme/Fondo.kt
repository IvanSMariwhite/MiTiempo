package com.example.mitiempo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.mitiempo.R

@Composable
fun Fondo(
    weatherCode: Int,
    isDay: Boolean
) {
    when {

        weatherCode == 0 && isDay -> {
        Image(
            painter = painterResource(R.drawable.despejado_dia),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

        weatherCode == 3 -> {
            Image(
                painter = painterResource(R.drawable.nublado_3),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

}

