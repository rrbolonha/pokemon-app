package com.example.pokemonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.pokemonapp.data.entities.PokemonType
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.infra.common.extensions.uppercaseFirst
import com.example.pokemonapp.ui.viewmodels.PokemonViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PokemonDetailFragment : Fragment() {

    private val binding by lazy { FragmentPokemonDetailBinding.inflate(layoutInflater) }
    private val viewModel by sharedViewModel<PokemonViewModel>()
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        getPokemon()
    }

    private fun getPokemon() {
        viewModel.pokemon(args.id)
    }

    private fun subscribeUi() {
        viewModel.pokemon.observe(viewLifecycleOwner) {
            setupPokemon(it)
        }
    }

    private fun setupPokemon(pokemon: Pokemon) {
        val pokemonType: PokemonType = try {
            PokemonType.valueOf(pokemon.typeName1!!.uppercase())
        } catch (e: Exception) {
            PokemonType.UNKNOWN
        }
        binding.root.setBackgroundColor(requireContext().getColor(pokemonType.surfaceColor()))
        binding.imageViewPokemon.load(pokemon.officialPath)
        binding.textViewCode.text = "#${pokemon.id}"
        binding.textViewName.text = pokemon.name.uppercaseFirst()
        binding.fabNameType1.text = pokemon.typeName1
        binding.fabNameType2.text = pokemon.typeName2
        handleTypeName(binding.chipTypeName1, pokemon.typeName1)
        handleTypeName(binding.chipTypeName2, pokemon.typeName2)
    }

    private fun handleTypeName(chip: Chip, typeName: String?) {
        val pokemonType: PokemonType = try {
            PokemonType.valueOf(typeName?.uppercase() ?: PokemonType.UNKNOWN.name)
        } catch (e: Exception) {
            PokemonType.UNKNOWN
        }
        typeName?.let {
            chip.setTextColor(requireContext().getColor(pokemonType.textColor()))
            chip.setChipBackgroundColorResource(pokemonType.backgroundColor())
            chip.text = it.uppercaseFirst()
            chip.visibility = View.VISIBLE
        } ?: apply {
            chip.visibility = View.GONE
        }
    }

}