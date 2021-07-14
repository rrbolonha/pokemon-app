package com.example.pokemonapp.ui.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokemonapp.R
import com.example.pokemonapp.data.entities.PokemonType
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.infra.common.extensions.uppercaseFirst
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip

open class PokemonViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val cardView by lazy { view.findViewById(R.id.card_view) as MaterialCardView }
    private val imageViewPhoto by lazy { view.findViewById(R.id.image_view_photo) as ImageView }
    private val textViewCode by lazy { view.findViewById(R.id.text_view_code) as TextView }
    private val textViewName by lazy { view.findViewById(R.id.text_view_name) as TextView }
    private val textViewDescription by lazy { view.findViewById(R.id.text_view_description) as TextView }
    private val chipTypeName1 by lazy { view.findViewById(R.id.chip_type_name_1) as Chip }
    private val chipTypeName2 by lazy { view.findViewById(R.id.chip_type_name_2) as Chip }

    open fun bind(pokemon: Pokemon, onClick: (Int) -> Unit) {
        cardView.setOnClickListener {
            onClick(pokemon.id)
        }
        textViewCode.text = "${fillWithZero(pokemon.id)}# "
        textViewName.text = pokemon.name.uppercaseFirst()
        textViewDescription.text = ""

        handleTypeName(view.context, chipTypeName1, pokemon.typeName1)
        handleTypeName(view.context, chipTypeName2, pokemon.typeName2)
        setupCard(view.context, cardView, pokemon.typeName1)

        pokemon.officialPath.isNotEmpty().apply {
            imageViewPhoto.load(pokemon.officialPath)
        }
    }

    private fun fillWithZero(id: Int): String {
        return when (id.toString().length) {
            1 -> "00$id"
            2 -> "0$id"
            else -> "$id"
        }
    }

    private fun setupCard(context: Context, cardView: MaterialCardView, typeName: String?) {
        typeName?.let {
            val pokemonType: PokemonType = try {
                PokemonType.valueOf(typeName.uppercase())
            } catch (e: Exception) {
                PokemonType.UNKNOWN
            }
//            cardView.strokeColor = view.context.getColor(pokemonType.textColor())
//            cardView.strokeWidth = 1
            cardView.setCardBackgroundColor(context.getColor(pokemonType.surfaceColor()))
        } ?: apply {
            cardView.visibility = View.GONE
        }
    }

    private fun handleTypeName(context: Context, chip: Chip, typeName: String?) {
        typeName?.let {
            val pokemonType: PokemonType = try {
                PokemonType.valueOf(typeName.uppercase())
            } catch (e: Exception) {
                PokemonType.UNKNOWN
            }
            chip.setTextColor(context.getColor(pokemonType.textColor()))
            chip.setChipBackgroundColorResource(pokemonType.backgroundColor())
            chip.text = it.uppercaseFirst()
            chip.visibility = View.VISIBLE
        } ?: apply {
            chip.visibility = View.GONE
        }
    }

}