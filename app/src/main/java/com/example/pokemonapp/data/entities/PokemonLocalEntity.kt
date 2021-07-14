package com.example.pokemonapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemons"
)
data class PokemonLocalEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type_name_1")
    val typeName1: String?,
    @ColumnInfo(name = "type_name_2")
    val typeName2: String?,
    @ColumnInfo(name = "official_path")
    val officialPath: String,
    @ColumnInfo(name = "art_path")
    val artPath: String
)