package com.example.pokemonapp.domain.di

import com.example.pokemonapp.domain.usecases.PokemonUseCase
import com.example.pokemonapp.domain.usecases.PokemonUseCaseImpl
import com.example.pokemonapp.domain.usecases.SeasonUseCase
import com.example.pokemonapp.domain.usecases.SeasonUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<PokemonUseCase> {
        PokemonUseCaseImpl(get())
    }

    factory<SeasonUseCase> {
        SeasonUseCaseImpl(get())
    }
}