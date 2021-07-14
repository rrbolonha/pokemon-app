package com.example.pokemonapp.infra.common.extensions

import com.example.pokemonapp.data.database.PokemonLocalDataSourceImpl
import com.example.pokemonapp.data.exceptions.GenericException
import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun <T> PokemonLocalDataSourceImpl.emit(
    exception: GenericException? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> T
): ResultWrapper<T> = withContext(dispatcher) {
    try {
        val data = call()
        ResultWrapper.Success(data)
    } catch (e: Exception) {
        Timber.e("on error: ${e.message} - $e")
        ResultWrapper.Error(resultException(exception, e))
    }
}

suspend fun <T, U> PokemonLocalDataSourceImpl.emit(
    call: suspend () -> T,
    exception: GenericException? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    onSuccess: (T) -> U
): ResultWrapper<U> = withContext(dispatcher) {
    try {
        val data = call()
        ResultWrapper.Success(onSuccess(data))
    } catch (e: Exception) {
        Timber.e("on error: ${e.message} - $e")
        ResultWrapper.Error(resultException(exception, e))
    }
}

private val resultException =
    { genericException: GenericException?, causeException: Exception ->
        genericException?.let {
            it.exception = causeException
            it
        } ?: causeException
    }