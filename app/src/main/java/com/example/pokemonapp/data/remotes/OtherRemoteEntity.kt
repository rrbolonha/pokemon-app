package com.example.pokemonapp.data.remotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtherRemoteEntity(
    @SerializedName("dream_world")
    val dreamWorldRemoteEntity: DreamWorldRemoteEntity,
    @SerializedName("official-artwork")
    val officialArtworkRemoteEntity: OfficialArtworkRemoteEntity
) : Parcelable