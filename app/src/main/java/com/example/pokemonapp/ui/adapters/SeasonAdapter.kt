package com.example.pokemonapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemSeasonBinding
import com.example.pokemonapp.domain.entities.Season
import com.example.pokemonapp.domain.entities.SeasonStatusType.*

class SeasonAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<SeasonViewHolder>() {

    private var seasons: List<Season> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val binding = ItemSeasonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SeasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val useMarginLeft = position == 1
        holder.bind(seasons[position], position, useMarginLeft) {
            onClick(it)
        }
    }

    override fun getItemCount(): Int = seasons.size

    fun submit(updatedList: List<Season>) {
        seasons = updatedList
    }

    fun handleSeasonEnable(position: Int) {
        val season = seasons[position]
        if (season.status != ACTIVATED) {
            season.status = if (season.status == NOT_SELECTED) SELECTED else NOT_SELECTED
            notifyItemChanged(position)
        }
    }

    fun hasSeasonsSelected(): Boolean =
        seasons.any { listOf(SELECTED, ACTIVATED).contains(it.status) }

}