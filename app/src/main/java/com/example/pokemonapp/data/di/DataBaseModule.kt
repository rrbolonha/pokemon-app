package com.example.pokemonapp.data.di

import com.example.pokemonapp.data.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(context = get()) }
    single { get<AppDatabase>().pokemonDao() }
    single { get<AppDatabase>().seasonDao() }
}