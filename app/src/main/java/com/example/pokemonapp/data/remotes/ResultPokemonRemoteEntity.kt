package com.example.pokemonapp.data.remotes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultPokemonRemoteEntity(
    val results: List<PokemonRemoteEntity>
) : Parcelable