package com.mikelau.core.common.utils

object PokemonFeature {
    const val nestedRoute = "pokemon_nested_route"
    const val pokemonScreenRoute = "pokemon_screen_route"
    const val deepLinkRoute = "pokedex://"
}

object PokemonDetailsFeature {
    const val nestedRoute = "pokemon_details_nested_route"
    const val pokemonDetailsScreenRoute = "pokemon_details/{id}"
    const val deepLinkRoute = "pokedex://pokemon/{id}"
}