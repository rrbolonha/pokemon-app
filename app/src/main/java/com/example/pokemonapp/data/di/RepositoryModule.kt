package com.example.pokemonapp.data.di

import com.example.pokemonapp.data.repositories.PokemonRepository
import com.example.pokemonapp.data.repositories.PokemonRepositoryImpl
import com.example.pokemonapp.data.repositories.SeasonRepository
import com.example.pokemonapp.data.repositories.SeasonRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<PokemonRepository> {
        PokemonRepositoryImpl(get(), get())
    }

    factory<SeasonRepository> {
        SeasonRepositoryImpl(get())
    }
}