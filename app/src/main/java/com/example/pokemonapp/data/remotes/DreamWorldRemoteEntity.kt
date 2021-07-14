package com.example.pokemonapp.data.remotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DreamWorldRemoteEntity(
    @SerializedName("front_default")
    val frontDefault: String
) : Parcelable