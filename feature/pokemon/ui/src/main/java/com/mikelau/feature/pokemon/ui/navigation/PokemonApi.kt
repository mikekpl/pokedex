package com.mikelau.feature.pokemon.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.mikelau.core.FeatureApi

interface PokemonApi : FeatureApi

class PokemonApiImpl : PokemonApi {
    override fun registerGraph(backStack: NavBackStack<NavKey>) {
        InternalPokemonFeatureApi.registerGraph(backStack)
    }
}