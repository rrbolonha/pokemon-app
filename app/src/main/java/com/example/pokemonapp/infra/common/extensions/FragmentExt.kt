package com.example.pokemonapp.infra.common.extensions

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.pokemonapp.ui.viewmodels.BaseViewModel

fun Fragment.showMessage(message: String) = showError(message)

fun Fragment.showError(message: String) {
    val builder = AlertDialog.Builder(requireContext())
    builder.setMessage(message)
        .setPositiveButton("OK") { _, _ ->

        }
    builder.create()
    builder.show()
}

fun Fragment.showRetry(message: String, onRetry: () -> Unit) {
    val builder = AlertDialog.Builder(requireContext())
    builder.setMessage(message)
        .setPositiveButton("Retry") { _, _ ->
            onRetry()
        }
        .setNegativeButton("Close") { _, _ ->
            // do nothing
        }
    builder.create()
    builder.show()
}

fun Fragment.setupError(viewModel: BaseViewModel) {
    viewModel.error.observe(viewLifecycleOwner) {
        showError(it)
    }
}

fun Fragment.setupRetry(viewModel: BaseViewModel, onRetry: () -> Unit) {
    viewModel.retry.observe(viewLifecycleOwner) {
        showRetry(it, onRetry)
    }
}

fun Fragment.setupLoader(viewModel: BaseViewModel, action: (isLoading: Boolean) -> Unit) {
    viewModel.isLoading.observe(viewLifecycleOwner) {
        action(it)
    }
}