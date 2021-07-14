package com.example.pokemonapp.data.di

import com.example.pokemonapp.data.database.PokemonLocalDataSource
import com.example.pokemonapp.data.database.PokemonLocalDataSourceImpl
import com.example.pokemonapp.data.database.PokemonRemoteDataSource
import com.example.pokemonapp.data.database.PokemonRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<PokemonRemoteDataSource> {
        PokemonRemoteDataSourceImpl(get())
    }

    factory<PokemonLocalDataSource> {
        PokemonLocalDataSourceImpl(get(), get())
    }
}