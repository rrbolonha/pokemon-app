package com.example.pokemonapp.data.database

import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.infra.common.ResultWrapper

interface PokemonLocalDataSource {

    suspend fun getAllPokemons(): ResultWrapper<List<PokemonLocalEntity>>

    suspend fun getPokemonById(id: Int): ResultWrapper<PokemonLocalEntity>

    suspend fun hasData(): ResultWrapper<Boolean>

    suspend fun insertPokemons(
        pokemonLocalEntityList: List<PokemonLocalEntity>
    ): ResultWrapper<Boolean>

    suspend fun deletePokemons(): ResultWrapper<Boolean>

    suspend fun insertSeasons(seasonList: List<SeasonLocalEntity>): ResultWrapper<Boolean>

    suspend fun updateSeasons(seasonLocalEntityList: List<SeasonLocalEntity>): ResultWrapper<Boolean>

    suspend fun getAllSeasons(): ResultWrapper<List<SeasonLocalEntity>>

    suspend fun getActiveSeasons(): ResultWrapper<List<SeasonLocalEntity>>


}