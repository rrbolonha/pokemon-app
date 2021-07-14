package com.example.pokemonapp.data.exceptions

open class GenericException(open var exception: Exception? = null) : Exception(exception)