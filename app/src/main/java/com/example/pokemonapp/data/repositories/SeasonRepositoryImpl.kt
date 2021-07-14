package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.data.database.PokemonLocalDataSource
import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.data.mappers.SeasonMapper
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeasonRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SeasonRepository {

    override suspend fun insert(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            val mapper = SeasonMapper.fromDomainToLocal(seasonList)
            localDataSource.insertSeasons(mapper)
        }

    override suspend fun update(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            val mapper = SeasonMapper.fromDomainToLocal(seasonList)
            localDataSource.updateSeasons(mapper)
        }

    override suspend fun getAll(): ResultWrapper<List<SeasonLocalEntity>> =
        withContext(dispatcher) {
            localDataSource.getAllSeasons()
        }

}