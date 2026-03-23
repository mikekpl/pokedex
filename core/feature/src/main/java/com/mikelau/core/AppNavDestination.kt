package com.mikelau.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppNavDestination : NavKey {
    @Serializable
    data object PokemonList : AppNavDestination

    @Serializable
    data class PokemonDetails(val id: String) : AppNavDestination
}