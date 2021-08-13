package com.example.pokemonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.databinding.FragmentPokemonListBinding
import com.example.pokemonapp.infra.common.extensions.setupErrorObserver
import com.example.pokemonapp.infra.common.extensions.setupLoaderObserver
import com.example.pokemonapp.infra.common.extensions.toVisibility
import com.example.pokemonapp.ui.adapters.PokemonAdapter
import com.example.pokemonapp.ui.viewmodels.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PokemonListFragment : Fragment() {

    private val binding by lazy { FragmentPokemonListBinding.inflate(layoutInflater) }
    private val viewModel by sharedViewModel<PokemonViewModel>()
    private val adapter by lazy {
        PokemonAdapter {
            onNavigateAction(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        subscribeUi()
        getPokemons()
    }

    private fun setupAdapter() {
        binding.recyclerViewPokemon.adapter = adapter
    }

    private fun subscribeUi() {
        setupErrorObserver(viewModel)
        setupLoaderObserver(viewModel) {
            binding.progressBar.visibility = it.toVisibility()
        }
        setupPokemonObserver()
    }

    private fun setupPokemonObserver() {
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            binding.recyclerViewPokemon.visibility = it.isNotEmpty().toVisibility()
            adapter.submit(it)
        }
    }

    private fun getPokemons() = viewModel.pokemons()

    private fun onNavigateAction(id: Int) {
        val action =
            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(id)
        findNavController().navigate(action)
    }

}