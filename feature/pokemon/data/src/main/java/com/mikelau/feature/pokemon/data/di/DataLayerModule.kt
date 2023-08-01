package com.mikelau.feature.pokemon.data.di

import com.mikelau.core.network.dataproviders.PokemonDataProviders
import com.mikelau.feature.pokemon.data.repository.PokemonRepositoryImpl
import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    fun providePokemonRepository(pokemonDataProviders: PokemonDataProviders): PokemonRepository {
        return PokemonRepositoryImpl(pokemonDataProviders)
    }

}