package com.mikelau.feature.pokemondetails.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mikelau.core.FeatureApi

interface PokemonDetailsApi: FeatureApi

class PokemonDetailsApiImpl: PokemonDetailsApi {

    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalPokemonDetailsApi.registerGraph(navController, navGraphBuilder)
    }

}