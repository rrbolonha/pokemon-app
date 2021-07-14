package com.example.pokemonapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonEndBinding
import com.example.pokemonapp.databinding.ItemPokemonStartBinding
import com.example.pokemonapp.domain.entities.Pokemon

class PokemonAdapter(private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    private var pokemons: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return when (viewType) {
            POKEMON_START -> {
                val binding = ItemPokemonStartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PokemonStartViewHolder(binding)
            }
            POKEMON_END -> {
                val binding = ItemPokemonEndBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PokemonEndViewHolder(binding)
            }
            else -> throw IllegalArgumentException("not supported item id")
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position]) {
            onClick(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) POKEMON_START else POKEMON_END
    }

    override fun getItemCount(): Int = pokemons.size

    fun submit(updatedList: List<Pokemon>) {
        pokemons = updatedList
    }

    companion object {
        private const val POKEMON_START = 0
        private const val POKEMON_END = 1
    }

}