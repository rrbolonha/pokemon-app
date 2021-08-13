package com.example.pokemonapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.domain.usecases.PokemonUseCase
import com.example.pokemonapp.domain.usecases.SeasonUseCase
import com.example.pokemonapp.infra.common.extensions.emit

class PokemonViewModel(
    private val pokemonUseCase: PokemonUseCase,
    private val seasonUseCase: SeasonUseCase
) : BaseViewModel() {

    val pokemonList: MutableLiveData<List<Pokemon>> by lazy {
        MutableLiveData<List<Pokemon>>()
    }

    val pokemon: MutableLiveData<Pokemon> by lazy {
        MutableLiveData<Pokemon>()
    }

    val seasonList: MutableLiveData<List<Season>> by lazy {
        MutableLiveData<List<Season>>()
    }

    fun pokemons() =
        emit(call = { pokemonUseCase.getAll() }) {
            pokemonList.postValue(it)
            handleLoading(false)
        }

    fun seasons() =
        emit(call = { seasonUseCase.getActiveSeasons() }) {
            seasonList.postValue(it)
        }

    fun pokemon(id: Int) =
        emit(call = { pokemonUseCase.getById(id) }) {
            pokemon.postValue(it)
        }

}