package com.mikelau.feature.pokemondetails.domain.di

import com.mikelau.feature.pokemondetails.domain.usecase.GetPokemonDetailsUseCase
import org.koin.dsl.module

val pokemonDetailsDomainModule = module {
    single { GetPokemonDetailsUseCase(get()) }
}