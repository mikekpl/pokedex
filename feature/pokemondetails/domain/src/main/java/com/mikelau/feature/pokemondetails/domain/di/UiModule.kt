package com.mikelau.feature.pokemondetails.domain.di

import com.mikelau.feature.pokemondetails.domain.repository.PokemonDetailsRepository
import com.mikelau.feature.pokemondetails.domain.usecase.GetPokemonDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideGetPokemonDetailsUseCase(pokemonDetailsRepository: PokemonDetailsRepository): GetPokemonDetailsUseCase {
        return GetPokemonDetailsUseCase(pokemonDetailsRepository)
    }

}