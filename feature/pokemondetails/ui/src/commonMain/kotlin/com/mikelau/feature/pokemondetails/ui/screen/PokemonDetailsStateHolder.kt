package com.mikelau.feature.pokemondetails.ui.screen

import com.mikelau.feature.pokemondetails.domain.model.PokemonDetails

data class PokemonDetailsStateHolder(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: PokemonDetails? = null
)
