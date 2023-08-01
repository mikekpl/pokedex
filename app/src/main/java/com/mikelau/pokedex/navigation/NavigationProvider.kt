package com.mikelau.pokedex.navigation

import com.mikelau.feature.pokemon.ui.navigation.PokemonApi
import com.mikelau.feature.pokemondetails.ui.navigation.PokemonDetailsApi

data class NavigationProvider(
    val pokemonApi: PokemonApi,
    val pokemonDetailsApi: PokemonDetailsApi
)
