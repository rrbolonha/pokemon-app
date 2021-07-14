package com.example.pokemonapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    val typeName1: String?,
    val typeName2: String?,
    val officialPath: String = "",
    val artPath: String = ""
) : Parcelable