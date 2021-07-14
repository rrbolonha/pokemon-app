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

    override suspend fun getAll(limit: Int, offSet: Int): ResultWrapper<List<Pokemon>> =
        withContext(dispatcher) {
            getLocalPokemons()
        }

    override suspend fun fetch(seasonList: List<Season>): ResultWrapper<Boolean> =
        withContext(dispatcher) {
            val hasData = (localDataSource.hasData() as ResultWrapper.Success).data
            if (hasData.not()) {
                // TODO: 7/13/2021 changed logic to take all limit
                val limit = seasonList.last().end
                when (val result = getRemotePokemons(limit)) {
                    is ResultWrapper.Success -> {
                        val insertResult =
                            insertPokemons(PokemonMapper.fromRemoteToLocal(result.data))
                        if (insertResult is ResultWrapper.Success) ResultWrapper.Success(true)
                        else ResultWrapper.Error(Exception("Problem to insert pokemons"))
                    }
                    is ResultWrapper.Error -> result
                }
            } else ResultWrapper.Success(true)
        }

    override suspend fun getById(id: Int): ResultWrapper<Pokemon> = withContext(dispatcher) {
        when (val result = localDataSource.getPokemonById(id)) {
            is ResultWrapper.Success -> {
                val mapper = PokemonMapper.fromLocalToDomain(result.data)
                ResultWrapper.Success(mapper)
            }
            is ResultWrapper.Error -> result
        }
    }

    override suspend fun delete(): ResultWrapper<Boolean> = withContext(dispatcher) {
        localDataSource.deletePokemons()
    }

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

    private suspend fun getRemotePokemons(limit: Int): ResultWrapper<List<PokemonRemoteEntity>> =
        withContext(dispatcher) {
            val pokemonList = arrayListOf<PokemonRemoteEntity>()
            for (current in 1..limit) {
                when (val result = getRemotePokemon(current.toString())) {
                    is ResultWrapper.Success -> pokemonList.add(result.data)
                    is ResultWrapper.Error -> ResultWrapper.Error(Exception("Not found pokemon $current"))
                }
            }
            ResultWrapper.Success(pokemonList)
        }

    private suspend fun getLocalPokemons() = withContext(dispatcher) {
        when (val result = localDataSource.getAllPokemons()) {
            is ResultWrapper.Success -> {
                try {
                    val mapper = PokemonMapper.fromLocalToDomain(result.data)
                    ResultWrapper.Success(mapper)
                } catch (e: Exception) {
                    ResultWrapper.Success(emptyList())
                }
            }
            is ResultWrapper.Error -> result
        }
    }

    private suspend fun insertPokemons(pokemonLocalEntityList: List<PokemonLocalEntity>) =
        withContext(dispatcher) {
            localDataSource.insertPokemons(pokemonLocalEntityList)
        }

}