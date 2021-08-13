package com.example.pokemonapp.domain.usecases

import com.example.pokemonapp.data.repositories.PokemonRepository
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonUseCaseImpl(
    private val repository: PokemonRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : PokemonUseCase {

    override suspend fun getAll(): ResultWrapper<List<Pokemon>> =
        repository.getAll()

    override suspend fun fetch(seasonList: List<Season>): ResultWrapper<Boolean> =
        repository.fetch(seasonList)

    override suspend fun getById(id: Int): ResultWrapper<Pokemon> =
        repository.getById(id)

    override suspend fun delete(): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            repository.delete()
        }

}