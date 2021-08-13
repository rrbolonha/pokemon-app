package com.example.pokemonapp.domain.entities

enum class SeasonStatusType {
    NOT_SELECTED {
        override fun value(): Int = 0
    },
    SELECTED {
        override fun value(): Int = 1
    },
    ACTIVATED {
        override fun value(): Int = 2
    };

    abstract fun value(): Int
}