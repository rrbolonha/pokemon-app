package com.example.pokemonapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.domain.entities.SeasonStatusType
import com.example.pokemonapp.domain.entities.SeasonStatusType.NOT_SELECTED
import com.example.pokemonapp.domain.entities.SeasonStatusType.SELECTED
import com.example.pokemonapp.domain.usecases.PokemonUseCase
import com.example.pokemonapp.domain.usecases.SeasonUseCase
import com.example.pokemonapp.infra.common.extensions.emit

class SeasonViewModel(
    private val seasonUseCase: SeasonUseCase,
    private val pokemonUseCase: PokemonUseCase
) : BaseViewModel() {

    val seasonList: MutableLiveData<List<Season>> by lazy {
        MutableLiveData<List<Season>>()
    }

    val isUpdatedSeasons: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val isCompletedFetch: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val isDeletedDatabase: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private val seasonsActivated =
        { status: SeasonStatusType -> seasonList.value!!.filter { it.status == status } }

    fun seasons() =
        emit(call = { seasonUseCase.getAll() },
            onError = { updateRetry("Not found seasons") }) {
            seasonList.postValue(it)
        }

    fun updateSeasons() =
        emit(call = {
            seasonUseCase.update(seasonList.value!!)
        }) {
            isUpdatedSeasons.postValue(it)
        }

    fun pokemons() =
        emit(call = {
            pokemonUseCase.fetch(seasonsActivated(SELECTED))
        }) {
            isCompletedFetch.postValue(it)
        }

    fun deleteDatabase() =
        emit(call = { pokemonUseCase.delete() }) { isDeletedPokemons ->
            if (isDeletedPokemons) {
                emit(call = { seasonUseCase.update(seasonsActivated(NOT_SELECTED)) }) {
                    isDeletedDatabase.postValue(it)
                }
            }
        }

}