package com.mikelau.feature.pokemondetails.ui.di

import com.mikelau.feature.pokemondetails.ui.screen.PokemonDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val pokemonDetailsUiModule = module {
    viewModel { PokemonDetailsViewModel(get()) }
}
