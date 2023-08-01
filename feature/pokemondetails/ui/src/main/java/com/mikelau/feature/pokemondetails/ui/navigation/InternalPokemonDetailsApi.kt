package com.mikelau.feature.pokemondetails.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.mikelau.core.FeatureApi
import com.mikelau.core.common.utils.PokemonDetailsFeature
import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsScreen
import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsViewModel

object InternalPokemonDetailsApi: FeatureApi {

    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(route = PokemonDetailsFeature.nestedRoute, startDestination = PokemonDetailsFeature.pokemonDetailsScreenRoute) {
            composable(
                PokemonDetailsFeature.pokemonDetailsScreenRoute,
                deepLinks = listOf(navDeepLink { uriPattern = PokemonDetailsFeature.deepLinkRoute })
            ) {
                val id = it.arguments?.getString("id")
                val viewModel = hiltViewModel<PokemonDetailsViewModel>()
                PokemonDetailsScreen(id = id.toString(), viewModel = viewModel, navController)
            }
        }
    }
}