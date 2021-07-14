package com.example.pokemonapp.domain.usecases

import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.infra.common.ResultWrapper

interface SeasonUseCase {

    suspend fun insert(seasonList: List<Season>): ResultWrapper<Boolean>

    suspend fun update(seasonList: List<Season>): ResultWrapper<Boolean>

    suspend fun getAll(): ResultWrapper<List<Season>>

    suspend fun getActiveSeasons(): ResultWrapper<List<Season>>

}