package com.example.pokemonapp.data.mappers

import com.example.pokemonapp.data.entities.SeasonLocalEntity
import com.example.pokemonapp.domain.entities.Season

object SeasonMapper {

    fun fromDomainToLocal(season: Season): SeasonLocalEntity =
        SeasonLocalEntity(season.id, season.title, season.start, season.end, season.isActive)

    fun fromDomainToLocal(seasonList: List<Season>): List<SeasonLocalEntity> =
        seasonList.map { fromDomainToLocal(it) }

    fun fromLocalToDomain(seasonLocalEntity: SeasonLocalEntity): Season =
        Season(
            seasonLocalEntity.title,
            seasonLocalEntity.start,
            seasonLocalEntity.end,
            seasonLocalEntity.id,
            seasonLocalEntity.isActive
        )

    fun fromLocalToDomain(seasonLocalEntityList: List<SeasonLocalEntity>): List<Season> =
        seasonLocalEntityList.map { fromLocalToDomain(it) }

}