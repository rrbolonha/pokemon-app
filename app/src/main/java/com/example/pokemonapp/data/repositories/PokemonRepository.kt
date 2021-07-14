package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper

interface PokemonRepository {

    suspend fun getAll(limit: Int, offSet: Int): ResultWrapper<List<Pokemon>>

    suspend fun fetch(seasonList: List<Season>): ResultWrapper<Boolean>

    suspend fun getById(id: Int): ResultWrapper<Pokemon>

    suspend fun delete(): ResultWrapper<Boolean>

}