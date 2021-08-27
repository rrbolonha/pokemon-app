package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.data.database.PokemonLocalDataSource
import com.example.pokemonapp.data.database.PokemonRemoteDataSource
import com.example.pokemonapp.data.mappers.PokemonMapper
import com.example.pokemonapp.data.remotes.PokemonRemoteEntity
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.domain.entities.SeasonStatusType.ACTIVATED
import com.example.pokemonapp.domain.entities.SeasonStatusType.NOT_SELECTED
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonRepository {

    override suspend fun getAll(): ResultWrapper<List<Pokemon>> =
        localDataSource.getAllPokemons()

    override suspend fun fetch(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            seasonList.forEach { season ->
                when (val result = getRemotePokemons(season)) {
                    is ResultWrapper.Success -> {
                        val isInserted = insertPokemons(result.data)
                        if (isInserted is ResultWrapper.Success) {
                            season.status = ACTIVATED
                        } else season.status = NOT_SELECTED
                    }
                    is ResultWrapper.Error -> season.status = NOT_SELECTED
                }
            }

            if (seasonList.all { it.status == ACTIVATED }) {
                localDataSource.updateSeasons(seasonList)
            } else ResultWrapper.Error(Exception("Problem to load data"))
        }

    override suspend fun getById(id: Int): ResultWrapper<Pokemon> =
        localDataSource.getPokemonById(id)

    override suspend fun delete(): ResultWrapper<Boolean> =
        localDataSource.deletePokemons()

    private suspend fun getRemotePokemon(code: String): ResultWrapper<PokemonRemoteEntity> =
        withContext(dispatcher) {
            when (val result = remoteDataSource.getPokemon(code)) {
                is ResultWrapper.Success -> {
                    result.data?.let {
                        ResultWrapper.Success(result.data)
                    } ?: ResultWrapper.Error(Exception("Pokemon not found $code"))
                }
                is ResultWrapper.Error -> ResultWrapper.Error(Exception("Pokemon not found $code"))
            }
        }

    private suspend fun getRemotePokemons(
        season: Season
    ): ResultWrapper<List<PokemonRemoteEntity>> =
        withContext(dispatcher) {
            val pokemonList = mutableListOf<PokemonRemoteEntity>()
            for (current in season.start..season.end) {
                when (val result = getRemotePokemon(current.toString())) {
                    is ResultWrapper.Success -> pokemonList.add(result.data)
                    is ResultWrapper.Error -> ResultWrapper.Error(Exception("Not found pokemon $current"))
                }
            }
            ResultWrapper.Success(pokemonList)
        }

    private suspend fun insertPokemons(pokemonRemoteEntityList: List<PokemonRemoteEntity>) =
        withContext(dispatcher) {
            val pokemonLocalEntityList = PokemonMapper.fromRemoteToLocal(pokemonRemoteEntityList)
            localDataSource.insertPokemons(pokemonLocalEntityList)
        }

}