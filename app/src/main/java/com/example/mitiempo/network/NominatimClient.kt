package com.example.mitiempo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NominatimClient {

    private const val BASE_URL = "https://nominatim.openstreetmap.org/"

    val api: NominatimApi by lazy {

        val httpClient = okhttp3.OkHttpClient.Builder()
            .addInterceptor { chain ->

                val request = chain.request()
                    .newBuilder()
                    .header(
                        "User-Agent",
                        "MiTiempo/1.0 (Android weather app)"
                    )
                    .build()

                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NominatimApi::class.java)
    }
}
