package com.example.pokemonapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    val id: Int = 0,
    val title: String,
    val start: Int,
    val end: Int,
    var status: SeasonStatusType,
    var limit: Int = 0,
    var offSet: Int = 0
) : Parcelable {
    init {
        offSet = start - 1
        limit = end - start
    }
}