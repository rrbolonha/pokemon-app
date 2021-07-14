package com.example.pokemonapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _retry = MutableLiveData<String>()
    val retry: LiveData<String> = _retry

    fun updateError(message: String) {
        handleLoading(false)
        _error.postValue(message)
    }

    fun updateRetry(message: String) {
        handleLoading(false)
        _retry.postValue(message)
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun handleLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }
}