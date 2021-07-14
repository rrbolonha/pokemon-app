package com.example.pokemonapp.data.mappers

import com.example.pokemonapp.data.entities.PokemonLocalEntity
import com.example.pokemonapp.data.remotes.PokemonRemoteEntity
import com.example.pokemonapp.domain.entities.Pokemon

object PokemonMapper {

    fun fromLocalToDomain(pokemonLocalEntity: PokemonLocalEntity): Pokemon =
        Pokemon(
            pokemonLocalEntity.id,
            pokemonLocalEntity.name,
            pokemonLocalEntity.typeName1,
            pokemonLocalEntity.typeName2,
            pokemonLocalEntity.officialPath,
            ""//pokemonLocalEntity.artPath
        )

    fun fromLocalToDomain(pokemonLocalEntityList: List<PokemonLocalEntity>): List<Pokemon> =
        pokemonLocalEntityList.map { fromLocalToDomain(it) }

    fun fromRemoteToLocal(pokemonRemoteEntityList: List<PokemonRemoteEntity>): List<PokemonLocalEntity> =
        pokemonRemoteEntityList.map { fromRemoteToLocal(it) }

    fun fromRemoteToLocal(pokemonRemoteEntity: PokemonRemoteEntity): PokemonLocalEntity =
        PokemonLocalEntity(
            pokemonRemoteEntity.id,
            pokemonRemoteEntity.name,
            pokemonRemoteEntity.typeRemoteEntityList.getOrNull(0)?.typeNameRemoteEntity?.name,
            pokemonRemoteEntity.typeRemoteEntityList.getOrNull(1)?.typeNameRemoteEntity?.name,
            pokemonRemoteEntity.spritesRemoteEntity.otherRemoteEntity.officialArtworkRemoteEntity.frontDefault,
            ""//pokemonRemoteEntity.spritesRemoteEntity.otherRemoteEntity.dreamWorldRemoteEntity.frontDefault
        )

}