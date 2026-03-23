@file:Suppress("DEPRECATION", "OPT_IN_USAGE")

package com.mikelau.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mikelau.core.AppNavDestination
import com.mikelau.feature.pokemon.ui.screen.PokemonScreen
import com.mikelau.feature.pokemon.ui.screen.PokemonViewModel
import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsScreen
import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsViewModel

@Composable
fun rememberAppNavBackStack(): NavBackStack<NavKey> =
    rememberNavBackStack(AppNavDestination.PokemonList)

@Suppress("UNCHECKED_CAST")
@Composable
fun AppNavGraph(backStack: NavBackStack<NavKey>) {
    val typedBackStack = backStack as NavBackStack<AppNavDestination>
    NavDisplay(
        backStack = typedBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        onBack = { typedBackStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is AppNavDestination.PokemonList -> NavEntry(key) {
                    val viewModel = hiltViewModel<PokemonViewModel>()
                    PokemonScreen(viewModel = viewModel, backStack = typedBackStack)
                }
                is AppNavDestination.PokemonDetails -> NavEntry(key) {
                    val viewModel = hiltViewModel<PokemonDetailsViewModel>()
                    PokemonDetailsScreen(id = key.id, viewModel = viewModel, backStack = typedBackStack)
                }
            }
        }
    )
}
