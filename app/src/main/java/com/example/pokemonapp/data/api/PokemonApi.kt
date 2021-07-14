package com.example.pokemonapp.data.api

import com.example.pokemonapp.data.remotes.PokemonRemoteEntity
import com.example.pokemonapp.data.remotes.ResultPokemonRemoteEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET(value = "pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offSet: Int
    ): Response<ResultPokemonRemoteEntity>

    @GET(value = "pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): Response<PokemonRemoteEntity>

}