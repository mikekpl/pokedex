package com.mikelau.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.mikelau.core.AppNavDestination
import com.mikelau.feature.pokemon.ui.screen.PokemonScreen
import com.mikelau.feature.pokemon.ui.screen.PokemonViewModel
import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsScreen
import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph() {
    val backStack = rememberNavBackStack(AppNavDestination.PokemonList)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            // Initial screen — Pokemon list
            entry<AppNavDestination.PokemonList> {
                val viewModel = koinViewModel<PokemonViewModel>()
                PokemonScreen(
                    viewModel = viewModel,
                    onPokemonClick = { pokemonId ->
                        backStack.add(AppNavDestination.PokemonDetails(id = pokemonId))
                    }
                )
            }

            // Detail screen — opened when a list item is clicked
            entry<AppNavDestination.PokemonDetails> { destination ->
                val viewModel = koinViewModel<PokemonDetailsViewModel>()
                PokemonDetailsScreen(
                    id = destination.id,
                    viewModel = viewModel,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}




