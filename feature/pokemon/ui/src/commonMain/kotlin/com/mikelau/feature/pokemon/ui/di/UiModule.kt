package com.mikelau.feature.pokemon.ui.di

import com.mikelau.feature.pokemon.ui.screen.PokemonViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val pokemonUiModule = module {
    viewModel { PokemonViewModel(get()) }
}
