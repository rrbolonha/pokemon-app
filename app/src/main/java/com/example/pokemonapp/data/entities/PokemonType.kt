package com.example.pokemonapp.data.entities

import com.example.pokemonapp.R

enum class PokemonType {
    GRASS {
        override fun backgroundColor(): Int = R.color.grass_light

        override fun textColor(): Int = R.color.grass_dark

        override fun surfaceColor(): Int = R.color.grass_surface
    },
    POISON {
        override fun backgroundColor(): Int = R.color.poison_light

        override fun textColor(): Int = R.color.poison_dark

        override fun surfaceColor(): Int = R.color.poison_surface
    },
    FIRE {
        override fun backgroundColor(): Int = R.color.fire_light

        override fun textColor(): Int = R.color.fire_dark

        override fun surfaceColor(): Int = R.color.fire_surface
    },
    FLYING {
        override fun backgroundColor(): Int = R.color.flying_light

        override fun textColor(): Int = R.color.flying_dark

        override fun surfaceColor(): Int = R.color.flying_surface
    },
    WATER {
        override fun backgroundColor(): Int = R.color.water_light

        override fun textColor(): Int = R.color.water_dark

        override fun surfaceColor(): Int = R.color.water_surface
    },
    BUG {
        override fun backgroundColor(): Int = R.color.bug_light

        override fun textColor(): Int = R.color.bug_dark

        override fun surfaceColor(): Int = R.color.bug_surface
    },
    FAIRY {
        override fun backgroundColor(): Int = R.color.fairy_light

        override fun textColor(): Int = R.color.fairy_dark

        override fun surfaceColor(): Int = R.color.fairy_surface
    },
    NORMAL {
        override fun backgroundColor(): Int = R.color.normal_light

        override fun textColor(): Int = R.color.normal_dark

        override fun surfaceColor(): Int = R.color.normal_surface
    },
    ELECTRIC {
        override fun backgroundColor(): Int = R.color.electric_light

        override fun textColor(): Int = R.color.electric_dark

        override fun surfaceColor(): Int = R.color.electric_surface
    },
    GROUND {
        override fun backgroundColor(): Int = R.color.ground_light

        override fun textColor(): Int = R.color.ground_dark

        override fun surfaceColor(): Int = R.color.ground_surface
    },
    FIGHTING {
        override fun backgroundColor(): Int = R.color.fighting_light

        override fun textColor(): Int = R.color.fighting_dark

        override fun surfaceColor(): Int = R.color.fighting_surface
    },
    PSYCHIC {
        override fun backgroundColor(): Int = R.color.psychic_light

        override fun textColor(): Int = R.color.psychic_dark

        override fun surfaceColor(): Int = R.color.psychic_surface
    },
    ROCK {
        override fun backgroundColor(): Int = R.color.rock_light

        override fun textColor(): Int = R.color.rock_dark

        override fun surfaceColor(): Int = R.color.rock_surface
    },
    STEEL {
        override fun backgroundColor(): Int = R.color.rock_light

        override fun textColor(): Int = R.color.rock_dark

        override fun surfaceColor(): Int = R.color.rock_surface
    },
    ICE {
        override fun backgroundColor(): Int = R.color.ice_light

        override fun textColor(): Int = R.color.ice_dark

        override fun surfaceColor(): Int = R.color.ice_surface
    },
    GHOST {
        override fun backgroundColor(): Int = R.color.ghost_light

        override fun textColor(): Int = R.color.ghost_dark

        override fun surfaceColor(): Int = R.color.ghost_surface
    },
    DRAGON {
        override fun backgroundColor(): Int = R.color.dragon_light

        override fun textColor(): Int = R.color.dragon_dark

        override fun surfaceColor(): Int = R.color.dragon_surface
    },
    UNKNOWN {
        override fun backgroundColor(): Int = R.color.black

        override fun textColor(): Int = R.color.black

        override fun surfaceColor(): Int = R.color.black
    };

    abstract fun backgroundColor(): Int
    abstract fun textColor(): Int
    abstract fun surfaceColor(): Int
}