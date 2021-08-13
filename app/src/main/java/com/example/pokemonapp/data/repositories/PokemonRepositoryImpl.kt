package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.data.database.PokemonLocalDataSource
import com.example.pokemonapp.data.database.PokemonRemoteDataSource
import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.mappers.PokemonMapper
import com.example.pokemonapp.data.remotes.PokemonRemoteEntity
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
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
        withContext(dispatcher) {
            when (val result = localDataSource.getAllPokemons()) {
                is ResultWrapper.Success -> {
                    try {
                        ResultWrapper.Success(result.data)
                    } catch (e: Exception) {
                        ResultWrapper.Success(emptyList())
                    }
                }
                is ResultWrapper.Error -> result
            }
        }

    override suspend fun fetch(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            seasonList.forEach { season ->
                val hasLocalData =
                    (localDataSource.hasData(season.start) as ResultWrapper.Success).data
                if (hasLocalData.not()) {
                    when (val result = getRemotePokemons(season)) {
                        is ResultWrapper.Success -> {
                            val pokemonLocalEntityList =
                                PokemonMapper.fromRemoteToLocal(result.data)

                            val insertResult = insertPokemons(pokemonLocalEntityList)
                            if (insertResult is ResultWrapper.Success) {
                                season.status = 2
                            } else season.status = 0
                        }
                        is ResultWrapper.Error -> {
                            season.status = 0
                        }
                    }
                } else season.status = 2
            }

            if (seasonList.all { it.status == 2 }) ResultWrapper.Success(true)
            else ResultWrapper.Error(Exception("Problem to load data"))
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

    private suspend fun insertPokemons(pokemonLocalEntityList: List<PokemonLocalEntity>) =
        withContext(dispatcher) {
            localDataSource.insertPokemons(pokemonLocalEntityList)
        }

}