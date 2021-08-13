package com.example.pokemonapp.domain.usecases

import com.example.pokemonapp.data.repositories.SeasonRepository
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeasonUseCaseImpl(
    private val repository: SeasonRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : SeasonUseCase {

    override suspend fun insert(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            seasonList.filter { it.status == 1 }.forEach { it.status = 2 }
            repository.insert(seasonList)
        }

    override suspend fun update(seasonList: List<Season>): ResultWrapper<Boolean> =
        repository.update(seasonList)

    override suspend fun getAll(): ResultWrapper<List<Season>> =
        withContext(dispatcher) {
            repository.getAll()
        }

    override suspend fun getActiveSeasons(): ResultWrapper<List<Season>> =
        withContext(dispatcher) {
            when (val result = getAll()) {
                is ResultWrapper.Success -> {
                    val seasonsActivated = result.data.filter { it.status == 2 }
                    ResultWrapper.Success(seasonsActivated)
                }
                is ResultWrapper.Error -> result
            }
        }

}