package com.example.pokemonapp.infra.common.extensions

import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.infra.common.ResultWrapper
import com.example.pokemonapp.ui.viewmodels.BaseViewModel
import kotlinx.coroutines.launch

fun <T> BaseViewModel.emit(
    call: suspend () -> ResultWrapper<T>,
    onError: (suspend (ResultWrapper.Error) -> Unit)? = null,
    onSuccess: suspend (T) -> Unit
) = viewModelScope.launch {
    when (val data = call()) {
        is ResultWrapper.Success -> onSuccess(data.data)
        is ResultWrapper.Error -> {
            onError?.let { it(data) } ?: updateError(data.exception.message!!)
        }
    }
}