package com.example.pokemonapp.infra.common

sealed class ResultWrapper<out R> {
    data class Success<out T>(val data: T, val code: Int = 0) : ResultWrapper<T>()
    data class Error(val exception: Exception, val code: Int = 0) : ResultWrapper<Nothing>()
}