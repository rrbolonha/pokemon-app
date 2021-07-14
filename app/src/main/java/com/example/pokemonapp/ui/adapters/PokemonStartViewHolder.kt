package com.example.pokemonapp.ui.adapters

import com.example.pokemonapp.databinding.ItemPokemonStartBinding
import com.example.pokemonapp.domain.entities.Pokemon

class PokemonStartViewHolder(
    private val binding: ItemPokemonStartBinding
) : PokemonViewHolder(binding.root) {

    override fun bind(pokemon: Pokemon, onClick: (Int) -> Unit) {
        super.bind(pokemon, onClick)
    }

}