package com.example.pokemonapp.ui.adapters

import com.example.pokemonapp.databinding.ItemPokemonEndBinding
import com.example.pokemonapp.domain.entities.Pokemon

class PokemonEndViewHolder(
    private val binding: ItemPokemonEndBinding
) : PokemonViewHolder(binding.root) {

    override fun bind(pokemon: Pokemon, onClick: (Int) -> Unit) {
        super.bind(pokemon, onClick)
    }

}