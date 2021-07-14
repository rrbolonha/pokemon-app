package com.example.pokemonapp.ui.viewmodels

import androidx.lifecycle.LiveData
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

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    private val _seasonList = MutableLiveData<List<Season>>()
    val seasonList: LiveData<List<Season>> = _seasonList

    fun pokemons() =
        emit(call = { pokemonUseCase.getAll(151) }) {
            _pokemonList.postValue(it)
            handleLoading(false)
        }

    fun seasons() =
        emit(call = { seasonUseCase.getActiveSeasons() }) {
            _seasonList.postValue(it)
        }

    fun pokemon(id: Int) =
        emit(call = { pokemonUseCase.getById(id) }) {
            _pokemon.postValue(it)
        }

}