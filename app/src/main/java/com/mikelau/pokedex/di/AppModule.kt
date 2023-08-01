package com.mikelau.pokedex.di

import com.mikelau.feature.pokemon.ui.navigation.PokemonApi
import com.mikelau.feature.pokemondetails.ui.navigation.PokemonDetailsApi
import com.mikelau.pokedex.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvider(pokemonApi: PokemonApi, pokemonDetailsApi: PokemonDetailsApi): NavigationProvider {
        return NavigationProvider(pokemonApi, pokemonDetailsApi)
    }

}

