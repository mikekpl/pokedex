package com.mikelau.feature.pokemon.data.di

import com.mikelau.feature.pokemon.data.repository.PokemonRepositoryImpl
import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import org.koin.dsl.module

val pokemonDataModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}
