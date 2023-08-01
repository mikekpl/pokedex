package com.mikelau.feature.pokemon.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mikelau.core.FeatureApi

interface PokemonApi : FeatureApi

class PokemonApiImpl : PokemonApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalPokemonFeatureApi.registerGraph(
            navController, navGraphBuilder
        )
    }
}