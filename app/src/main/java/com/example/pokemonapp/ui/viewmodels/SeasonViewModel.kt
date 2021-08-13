package com.example.pokemonapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.domain.usecases.PokemonUseCase
import com.example.pokemonapp.domain.usecases.SeasonUseCase
import com.example.pokemonapp.infra.common.ResultWrapper
import com.example.pokemonapp.infra.common.extensions.emit
import kotlinx.coroutines.launch

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

    private val seasonsSelected =
        { status: Int -> seasonList.value!!.filter { it.status == status } }

    private val _isDeletedDatabase = MutableLiveData<Boolean>()
    val isDeletedDatabase: LiveData<Boolean> = _isDeletedDatabase

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
            pokemonUseCase.fetch(seasonsSelected(1))
        }) {
            isCompletedFetch.postValue(it)
        }

    fun deleteDatabase() = viewModelScope.launch {
        when (val step1 = pokemonUseCase.delete()) {
            is ResultWrapper.Success -> {
                if (step1.data) {
                    when (val step2 = seasonUseCase.update(seasonsSelected(0))) {
                        is ResultWrapper.Success -> {
                            _isDeletedDatabase.postValue(step2.data!!)
                        }
                        is ResultWrapper.Error -> updateError("Can't update seasons")
                    }
                } else updateError("Can't delete pokemons")
            }
            is ResultWrapper.Error -> updateError("Can't delete pokemons")
        }
    }

}