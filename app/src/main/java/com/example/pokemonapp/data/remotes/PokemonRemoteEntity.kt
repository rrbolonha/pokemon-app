package com.example.pokemonapp.data.remotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PokemonRemoteEntity(
    val id: Int,
    val name: String,
    @SerializedName("sprites")
    val spritesRemoteEntity: SpritesRemoteEntity,
    @SerializedName("types")
    val typeRemoteEntityList: List<TypeRemoteEntity>
) : Parcelable