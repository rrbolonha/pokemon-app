package com.example.pokemonapp.data.database

import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper

interface PokemonLocalDataSource : BaseDataSource {

    suspend fun getAllPokemons(): ResultWrapper<List<Pokemon>>

    suspend fun getPokemonById(id: Int): ResultWrapper<Pokemon>

    suspend fun hasData(id: Int): ResultWrapper<Boolean>

    suspend fun insertPokemons(
        pokemonLocalEntityList: List<PokemonLocalEntity>
    ): ResultWrapper<Boolean>

    suspend fun deletePokemons(): ResultWrapper<Boolean>

    suspend fun insertSeasons(seasonList: List<SeasonLocalEntity>): ResultWrapper<Boolean>

    suspend fun updateSeasons(seasonLocalEntityList: List<SeasonLocalEntity>): ResultWrapper<Boolean>

    suspend fun getAllSeasons(): ResultWrapper<List<Season>>

}