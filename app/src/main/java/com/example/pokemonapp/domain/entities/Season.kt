package com.example.pokemonapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    val title: String,
    val start: Int,
    val end: Int,
    val id: Int = 0,
    var isActive: Boolean = false
) : Parcelable