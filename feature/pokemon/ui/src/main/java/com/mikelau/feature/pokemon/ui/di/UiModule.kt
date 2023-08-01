package com.mikelau.feature.pokemon.ui.di

import com.mikelau.feature.pokemon.ui.navigation.PokemonApi
import com.mikelau.feature.pokemon.ui.navigation.PokemonApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun providePokemonApi(): PokemonApi {
        return PokemonApiImpl()
    }

}