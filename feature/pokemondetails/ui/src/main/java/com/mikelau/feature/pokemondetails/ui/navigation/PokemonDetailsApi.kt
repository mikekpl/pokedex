package com.mikelau.feature.pokemondetails.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.mikelau.core.FeatureApi

interface PokemonDetailsApi : FeatureApi

class PokemonDetailsApiImpl : PokemonDetailsApi {
    override fun registerGraph(backStack: NavBackStack<NavKey>) {
        InternalPokemonDetailsApi.registerGraph(backStack)
    }
}
