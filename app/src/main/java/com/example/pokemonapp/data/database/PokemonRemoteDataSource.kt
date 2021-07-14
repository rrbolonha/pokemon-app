package com.example.pokemonapp.data.database

import com.example.pokemonapp.data.remotes.PokemonRemoteEntity
import com.example.pokemonapp.data.remotes.ResultPokemonRemoteEntity
import com.example.pokemonapp.infra.common.ResultWrapper

interface PokemonRemoteDataSource {

    suspend fun getPokemon(name: String): ResultWrapper<PokemonRemoteEntity?>

    suspend fun getPokemons(
        limit: Int,
        offSet: Int
    ): ResultWrapper<ResultPokemonRemoteEntity?>

}

