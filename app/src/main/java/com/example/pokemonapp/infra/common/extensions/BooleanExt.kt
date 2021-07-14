package com.example.pokemonapp.infra.common.extensions

import android.view.View

fun Boolean.toVisibility(): Int =
    if (this) View.VISIBLE else View.GONE