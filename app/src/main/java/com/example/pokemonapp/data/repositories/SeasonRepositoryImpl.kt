package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.data.database.PokemonLocalDataSource
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SeasonRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SeasonRepository {

    override suspend fun insert(seasonList: List<Season>): ResultWrapper<Boolean> =
        localDataSource.insertSeasons(seasonList)

    override suspend fun update(seasonList: List<Season>): ResultWrapper<Boolean> =
        localDataSource.updateSeasons(seasonList)

    override suspend fun getAll(): ResultWrapper<List<Season>> =
        localDataSource.getAllSeasons()

}