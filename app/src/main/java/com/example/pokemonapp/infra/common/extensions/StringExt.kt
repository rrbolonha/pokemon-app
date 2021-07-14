package com.example.pokemonapp.infra.common.extensions

fun String.uppercaseFirst(): String = this.replaceFirst(
    this[0],
    this[0].uppercaseChar()
)