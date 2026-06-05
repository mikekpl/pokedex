package com.mikelau.feature.pokemondetails.data.di

import com.mikelau.feature.pokemondetails.data.repository.PokemonDetailsRepositoryImpl
import com.mikelau.feature.pokemondetails.domain.repository.PokemonDetailsRepository
import org.koin.dsl.module

val pokemonDetailsDataModule = module {
    single<PokemonDetailsRepository> { PokemonDetailsRepositoryImpl(get()) }
}
