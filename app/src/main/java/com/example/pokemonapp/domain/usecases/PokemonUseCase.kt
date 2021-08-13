package com.example.pokemonapp.domain.usecases

import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper

interface PokemonUseCase : BaseUseCase {

    suspend fun getAll(): ResultWrapper<List<Pokemon>>

    suspend fun fetch(seasonList: List<Season>): ResultWrapper<Boolean>

    suspend fun getById(id: Int): ResultWrapper<Pokemon>

    suspend fun delete(): ResultWrapper<Boolean>

}