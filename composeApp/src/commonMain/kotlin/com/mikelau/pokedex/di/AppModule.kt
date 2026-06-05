package com.mikelau.pokedex.di

import com.mikelau.core.network.di.networkModule
import com.mikelau.feature.pokemon.data.di.pokemonDataModule
import com.mikelau.feature.pokemon.domain.di.pokemonDomainModule
import com.mikelau.feature.pokemon.ui.di.pokemonUiModule
import com.mikelau.feature.pokemondetails.data.di.pokemonDetailsDataModule
import com.mikelau.feature.pokemondetails.domain.di.pokemonDetailsDomainModule
import com.mikelau.feature.pokemondetails.ui.di.pokemonDetailsUiModule
import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        pokemonDataModule,
        pokemonDomainModule,
        pokemonUiModule,
        pokemonDetailsDataModule,
        pokemonDetailsDomainModule,
        pokemonDetailsUiModule
    )
}
