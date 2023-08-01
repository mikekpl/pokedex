package com.mikelau.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mikelau.core.network.ApiService
import com.mikelau.core.network.dataproviders.PokemonDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideApiService(): ApiService {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build().create(ApiService::class.java)
    }

    @Provides
    fun providePokemonDataProvider(apiService: ApiService): PokemonDataProviders {
        return PokemonDataProviders(apiService)
    }

}