package com.example.pokemonapp.data.mappers

import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.domain.entities.Season

object SeasonMapper {

    private fun fromDomainToLocal(season: Season): SeasonLocalEntity =
        SeasonLocalEntity(season.id, season.title, season.start, season.end, season.status)

    fun fromDomainToLocal(seasonList: List<Season>): List<SeasonLocalEntity> =
        seasonList.map { fromDomainToLocal(it) }

    private fun fromLocalToDomain(seasonLocalEntity: SeasonLocalEntity): Season =
        Season(
            seasonLocalEntity.id,
            seasonLocalEntity.title,
            seasonLocalEntity.start,
            seasonLocalEntity.end,
            seasonLocalEntity.status
        )

    fun fromLocalToDomain(seasonLocalEntityList: List<SeasonLocalEntity>): List<Season> =
        seasonLocalEntityList.map { fromLocalToDomain(it) }

}