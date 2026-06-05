package com.mikelau.feature.pokemon.ui.navigation

import com.mikelau.core.FeatureApi

interface PokemonApi : FeatureApi

class PokemonApiImpl : PokemonApi {
    override fun registerGraph() {
        InternalPokemonFeatureApi.registerGraph()
    }
}

