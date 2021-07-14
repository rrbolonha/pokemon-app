package com.example.pokemonapp.data.api

import com.example.pokemonapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PokemonApiClient {

    private lateinit var retrofit: Retrofit
    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    fun init() {
        val httpClient = OkHttpClient.Builder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        httpClient.addInterceptor { chain ->
            chain.run {
                val requestBuilder = request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                chain.proceed(requestBuilder.build())
            }
        }

        val gson = GsonBuilder().setDateFormat(dateFormat).create()

        retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}