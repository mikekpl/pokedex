package com.mikelau.feature.pokemondetails.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.mikelau.core.FeatureApi

object InternalPokemonDetailsApi : FeatureApi {
    override fun registerGraph(backStack: NavBackStack<NavKey>) {
        // Nav3 routing is handled centrally in AppNavGraph via NavDisplay.
    }
}
