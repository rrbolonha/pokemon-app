package com.example.pokemonapp.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ItemSeasonBinding
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.domain.entities.SeasonStatusType.ACTIVATED
import com.example.pokemonapp.domain.entities.SeasonStatusType.SELECTED

class SeasonViewHolder(private val binding: ItemSeasonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        season: Season,
        position: Int,
        useMarginLeft: Boolean,
        onClick: (Int) -> Unit
    ) {
        binding.root.setOnClickListener { onClick(position) }
        binding.marginStart.visibility = if (useMarginLeft) View.VISIBLE else View.GONE
        val photo =
            if (listOf(SELECTED, ACTIVATED).contains(season.status)) {
                R.drawable.pokeball_enabled
            } else R.drawable.pokeball_disabled
        binding.imageViewSeason.setImageResource(photo)
        binding.textViewTitle.text = season.title
        binding.textViewDescription.text = "${season.start} - ${season.end}"
    }

}