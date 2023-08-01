package com.mikelau.feature.pokemondetails.ui.di

import com.mikelau.feature.pokemondetails.ui.navigation.PokemonDetailsApi
import com.mikelau.feature.pokemondetails.ui.navigation.PokemonDetailsApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun providePokemonDetailsApi(): PokemonDetailsApi {
        return PokemonDetailsApiImpl()
    }

}