package com.example.pokemonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.databinding.FragmentLoadingJourneyBinding
import com.example.pokemonapp.infra.common.extensions.setupErrorObserver
import com.example.pokemonapp.infra.common.extensions.showError
import com.example.pokemonapp.ui.viewmodels.SeasonViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class LoadingJourneyFragment : Fragment() {

    private val binding by lazy { FragmentLoadingJourneyBinding.inflate(layoutInflater) }
    private val viewModel by sharedViewModel<SeasonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        getPokemons()
    }

    private fun onNavigateAction() {
        val action =
            LoadingJourneyFragmentDirections.actionLoadingJourneyFragmentToPokemonListFragment()
        findNavController().navigate(action)
    }

    private fun subscribeUi() {
        setupErrorObserver(viewModel)
        setupCompletedFetch()
    }

    private fun setupCompletedFetch() {
        viewModel.isCompletedFetch.observe(viewLifecycleOwner) {
            Timber.d("completed fetch: $it")
            if (it) onNavigateAction()
            else showError("Fetch pokemons failed")
        }
    }

    private fun getPokemons() {
        viewModel.pokemons()
    }

}