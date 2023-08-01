package com.mikelau.feature.pokemon.ui.screen

import com.mikelau.feature.pokemon.domain.model.Pokemon

data class PokemonStateHolder(
    val isLoading: Boolean = false,
    val data: List<Pokemon>? = null,
    val error: String = ""
)
