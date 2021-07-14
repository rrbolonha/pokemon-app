package com.example.pokemonapp.data.remotes

import com.example.pokemonapp.infra.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRemoteDataSource(private val dispatcher: CoroutineDispatcher) {

    suspend fun <T> http(request: suspend () -> Response<T>): ResultWrapper<T?> =
        withContext(dispatcher) {
            val response = request()
            response.errorBody()?.let {
                return@withContext ResultWrapper.Error(exception = Exception(it?.string()))
            }
            response.isSuccessful?.let {
                return@withContext ResultWrapper.Success(data = response.body())
            }
        }

}
