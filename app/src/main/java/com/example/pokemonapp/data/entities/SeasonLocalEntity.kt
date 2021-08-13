package com.example.pokemonapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemonapp.domain.entities.SeasonStatusType

@Entity(
    tableName = "seasons"
)
data class SeasonLocalEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "start")
    val start: Int,
    @ColumnInfo(name = "end")
    val end: Int,
    @ColumnInfo(name = "status")
    val status: SeasonStatusType
)