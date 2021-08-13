package com.example.pokemonapp.domain.entities

import androidx.room.TypeConverter

class SeasonsStatusTypeConverter {

    @TypeConverter
    fun fromTypeToInt(type: SeasonStatusType) = type.value()

    @TypeConverter
    fun fromIntToType(value: Int) = SeasonStatusType.values().first { it.value() == value }

}