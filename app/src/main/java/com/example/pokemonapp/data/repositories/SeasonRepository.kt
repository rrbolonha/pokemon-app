package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper

interface SeasonRepository {

    suspend fun insert(seasonList: List<Season>): ResultWrapper<Boolean>

    suspend fun update(seasonList: List<Season>): ResultWrapper<Boolean>

    suspend fun getAll(): ResultWrapper<List<SeasonLocalEntity>>

}