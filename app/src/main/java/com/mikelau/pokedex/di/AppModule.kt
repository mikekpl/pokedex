@file:Suppress("unused")

package com.mikelau.pokedex.di

import com.mikelau.feature.pokemon.ui.navigation.PokemonApi
import com.mikelau.feature.pokemon.ui.navigation.PokemonApiImpl
import com.mikelau.feature.pokemondetails.ui.navigation.PokemonDetailsApi
import com.mikelau.feature.pokemondetails.ui.navigation.PokemonDetailsApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providePokemonApi(): PokemonApi = PokemonApiImpl()

    @Provides
    fun providePokemonDetailsApi(): PokemonDetailsApi = PokemonDetailsApiImpl()
}

