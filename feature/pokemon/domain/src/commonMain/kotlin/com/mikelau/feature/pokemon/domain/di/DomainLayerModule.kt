package com.mikelau.feature.pokemon.domain.di

import com.mikelau.feature.pokemon.domain.usecase.GetPokemonListUseCase
import org.koin.dsl.module

val pokemonDomainModule = module {
    single { GetPokemonListUseCase(get()) }
}