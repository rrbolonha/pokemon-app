package com.example.pokemonapp.data.database

import com.example.pokemonapp.data.dao.PokemonDao
import com.example.pokemonapp.data.dao.SeasonDao
import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.mappers.PokemonMapper
import com.example.pokemonapp.data.mappers.SeasonMapper
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper
import com.example.pokemonapp.infra.common.extensions.emit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val seasonDao: SeasonDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonLocalDataSource {

    override suspend fun getAllPokemons(): ResultWrapper<List<Pokemon>> =
        emit(call = { pokemonDao.getAll() }) {
            PokemonMapper.fromLocalToDomain(it)
        }

    override suspend fun getPokemonById(id: Int): ResultWrapper<Pokemon> =
        emit(call = { pokemonDao.getById(id) }) {
            PokemonMapper.fromLocalToDomain(it)
        }

    override suspend fun hasData(id: Int): ResultWrapper<Boolean> =
        emit { pokemonDao.hasData(id) }

    override suspend fun insertPokemons(
        pokemonLocalEntityList: List<PokemonLocalEntity>
    ): ResultWrapper<Boolean> =
        emit(call = { pokemonDao.insert(pokemonLocalEntityList) }) {
            true
        }

    override suspend fun deletePokemons(): ResultWrapper<Boolean> =
        emit(call = { pokemonDao.delete() }) {
            true
        }

    override suspend fun insertSeasons(seasonList: List<Season>): ResultWrapper<Boolean> =
        emit(call = {
            val seasonLocalEntityList = SeasonMapper.fromDomainToLocal(seasonList)
            seasonDao.insert(seasonLocalEntityList)
        }) {
            true
        }

    override suspend fun updateSeasons(
        seasonList: List<Season>
    ): ResultWrapper<Boolean> =
        emit(call = {
            val seasonLocalEntityList = SeasonMapper.fromDomainToLocal(seasonList)
            seasonDao.update(seasonLocalEntityList)
        }) {
            true
        }

    override suspend fun getAllSeasons(): ResultWrapper<List<Season>> =
        emit(call = { seasonDao.getAll() }) {
            SeasonMapper.fromLocalToDomain(it)
        }

}