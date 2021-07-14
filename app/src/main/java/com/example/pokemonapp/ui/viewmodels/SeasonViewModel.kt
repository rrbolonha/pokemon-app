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

    private val _seasonList = MutableLiveData<List<Season>>()
    val seasonList: LiveData<List<Season>> = _seasonList

    private val _updatedSeasons = MutableLiveData<Boolean>()
    val updatedSeasons: LiveData<Boolean> = _updatedSeasons

    private val _completedFetch = MutableLiveData<Boolean>()
    val completedFetch: LiveData<Boolean> = _completedFetch

    private val _isDeletedDatabase = MutableLiveData<Boolean>()
    val isDeletedDatabase: LiveData<Boolean> = _isDeletedDatabase

    fun seasons() =
        emit(call = { seasonUseCase.getAll() }, onError = { updateRetry("Not found seasons") }) {
            _seasonList.postValue(it)
        }

    fun updateSeasons() =
        emit(call = {
            seasonUseCase.update(_seasonList.value ?: emptyList())
        }) {
            _updatedSeasons.postValue(it)
        }

    fun getPokemons() =
        emit(call = {
            pokemonUseCase.fetch(_seasonList.value?.filter { it.isActive } ?: emptyList())
        }) {
            _completedFetch.postValue(it)
        }

    fun deleteDatabase() = viewModelScope.launch {
        when (val step1 = pokemonUseCase.delete()) {
            is ResultWrapper.Success -> {
                if (step1.data) {
                    val seasons = _seasonList.value?.onEach { it.isActive = false } ?: emptyList()
                    when (val step2 = seasonUseCase.update(seasons)) {
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

    // adicionar a regra aqui
    //fun hasSeasonsSelected()

}