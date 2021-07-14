package com.example.pokemonapp.data.database

import com.example.pokemonapp.data.api.PokemonApi
import com.example.pokemonapp.data.remotes.BaseRemoteDataSource
import com.example.pokemonapp.data.remotes.PokemonRemoteEntity
import com.example.pokemonapp.data.remotes.ResultPokemonRemoteEntity
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PokemonRemoteDataSourceImpl(
    private val api: PokemonApi,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonRemoteDataSource, BaseRemoteDataSource(dispatcher) {

    override suspend fun getPokemons(
        limit: Int,
        offSet: Int
    ): ResultWrapper<ResultPokemonRemoteEntity?> =
        http { api.getPokemons(limit = limit, offSet = offSet) }

    override suspend fun getPokemon(
        name: String
    ): ResultWrapper<PokemonRemoteEntity?> =
        http { api.getPokemon(name = name) }

}