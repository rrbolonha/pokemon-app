package com.example.pokemonapp.data.di

import com.example.pokemonapp.data.api.PokemonApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://pokeapi.co/api/v2/"

val apiModule = module {
    factory { providesOkHttpClient() }

    single {
        createWebService<PokemonApi>(
            okHttpClient = get(),
            url = BASE_URL
        )
    }
}

val httpLoggingModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        setOf(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
    }
}

fun providesOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            chain.run {
                chain.proceed(providesInterceptor(request()))
            }
        }
        .build()

fun providesInterceptor(request: Request): Request =
    request.newBuilder()
        .addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json")
        .build()


inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)