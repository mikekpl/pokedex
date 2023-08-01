package com.mikelau.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mikelau.core.common.utils.PokemonFeature

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {

    NavHost(navController = navController, startDestination = PokemonFeature.nestedRoute){
        navigationProvider.pokemonApi.registerGraph(
            navController,this
        )

        navigationProvider.pokemonDetailsApi.registerGraph(
            navController,this
        )
    }
}