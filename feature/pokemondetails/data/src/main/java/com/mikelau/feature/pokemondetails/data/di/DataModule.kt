package com.mikelau.feature.pokemondetails.data.di

import com.mikelau.core.network.dataproviders.PokemonDataProviders
import com.mikelau.feature.pokemondetails.data.repository.PokemonDetailsRepositoryImpl
import com.mikelau.feature.pokemondetails.domain.repository.PokemonDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun providePokemonDetailsRepository(pokemonDataProviders: PokemonDataProviders): PokemonDetailsRepository {
        return PokemonDetailsRepositoryImpl(pokemonDataProviders)
    }

}