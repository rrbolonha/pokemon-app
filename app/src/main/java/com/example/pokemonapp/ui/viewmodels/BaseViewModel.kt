package com.example.pokemonapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val retry: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun updateError(message: String) {
        handleLoading(false)
        error.postValue(message)
    }

    fun updateRetry(message: String) {
        handleLoading(false)
        retry.postValue(message)
    }

    fun handleLoading(loading: Boolean) {
        isLoading.postValue(loading)
    }
}