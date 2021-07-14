package com.example.pokemonapp.domain.usecases

import com.example.pokemonapp.data.mappers.SeasonMapper
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
            repository.insert(seasonList)
        }

    override suspend fun update(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            repository.update(seasonList)
        }

    override suspend fun getAll(): ResultWrapper<List<Season>> =
        withContext(dispatcher) {
            when (val result = repository.getAll()) {
                is ResultWrapper.Success -> {
                    val mapper = SeasonMapper.fromLocalToDomain(result.data)
                    ResultWrapper.Success(mapper)
                }
                is ResultWrapper.Error -> result
            }
        }

    override suspend fun getActiveSeasons(): ResultWrapper<List<Season>> =
        withContext(dispatcher) {
            when (val result = repository.getAll()) {
                is ResultWrapper.Success -> {
                    val mapper = SeasonMapper.fromLocalToDomain(result.data)
                    val filtered = mapper.filter { it.isActive }
                    ResultWrapper.Success(filtered)
                }
                is ResultWrapper.Error -> result
            }
        }

}