package com.mikelau.feature.pokemondetails.ui.navigation

import com.mikelau.core.FeatureApi

interface PokemonDetailsApi : FeatureApi

class PokemonDetailsApiImpl : PokemonDetailsApi {
    override fun registerGraph() {
        InternalPokemonDetailsApi.registerGraph()
    }
}


