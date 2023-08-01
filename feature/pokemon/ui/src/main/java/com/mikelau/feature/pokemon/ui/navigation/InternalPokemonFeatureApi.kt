package com.mikelau.feature.pokemon.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.mikelau.core.FeatureApi
import com.mikelau.core.common.utils.PokemonFeature
import com.mikelau.feature.pokemon.ui.screen.PokemonScreen
import com.mikelau.feature.pokemon.ui.screen.PokemonViewModel

internal object InternalPokemonFeatureApi : FeatureApi {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(startDestination = PokemonFeature.pokemonScreenRoute, route = PokemonFeature.nestedRoute) {
            composable(
                PokemonFeature.pokemonScreenRoute,
                deepLinks = listOf(navDeepLink { uriPattern = PokemonFeature.deepLinkRoute })
            ) {
                val viewModel = hiltViewModel<PokemonViewModel>()
                PokemonScreen(viewModel = viewModel, navController)
            }
        }
    }
}