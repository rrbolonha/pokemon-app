package com.example.pokemonapp.data.database

import com.example.pokemonapp.data.dao.PokemonDao
import com.example.pokemonapp.data.dao.SeasonDao
import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.infra.common.ResultWrapper
import com.example.pokemonapp.infra.common.extensions.emit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val seasonDao: SeasonDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonLocalDataSource {

    override suspend fun getAllPokemons(): ResultWrapper<List<PokemonLocalEntity>> =
        emit { pokemonDao.getAll() }

    override suspend fun getPokemonById(id: Int): ResultWrapper<PokemonLocalEntity> =
        emit { pokemonDao.getById(id) }

    override suspend fun hasData(): ResultWrapper<Boolean> =
        emit { pokemonDao.hasData() }

    override suspend fun insertPokemons(pokemonLocalEntityList: List<PokemonLocalEntity>): ResultWrapper<Boolean> =
        emit(call = { pokemonDao.insert(pokemonLocalEntityList) }) {
            true
        }

    override suspend fun deletePokemons(): ResultWrapper<Boolean> =
        emit(call = { pokemonDao.delete() }) {
            true
        }

    override suspend fun insertSeasons(seasonList: List<SeasonLocalEntity>): ResultWrapper<Boolean> =
        emit(call = { seasonDao.insert(seasonList) }) {
            true
        }

    override suspend fun updateSeasons(seasonLocalEntityList: List<SeasonLocalEntity>): ResultWrapper<Boolean> =
        emit(call = { seasonDao.update(seasonLocalEntityList) }) {
            true
        }

    override suspend fun getAllSeasons(): ResultWrapper<List<SeasonLocalEntity>> =
        emit { seasonDao.getAll() }

    override suspend fun getActiveSeasons(): ResultWrapper<List<SeasonLocalEntity>> =
        emit { seasonDao.getActiveSeasons(true) }

}