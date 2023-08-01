package com.mikelau.feature.pokemon.domain.di

import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import com.mikelau.feature.pokemon.domain.usecase.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Provides
    fun provideGetPokemonListUseCase(pokemonRepository: PokemonRepository): GetPokemonListUseCase {
        return GetPokemonListUseCase(pokemonRepository)
    }

}