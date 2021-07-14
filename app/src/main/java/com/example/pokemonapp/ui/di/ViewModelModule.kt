package com.example.pokemonapp.ui.di

import com.example.pokemonapp.ui.viewmodels.PokemonViewModel
import com.example.pokemonapp.ui.viewmodels.SeasonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SeasonViewModel(get(), get())
    }

    viewModel {
        PokemonViewModel(get(), get())
    }

}