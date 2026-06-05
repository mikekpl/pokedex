package com.mikelau.core.network.di

import com.mikelau.core.network.ApiService
import com.mikelau.core.network.dataproviders.PokemonDataProviders
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { 
                    ignoreUnknownKeys = true 
                    useAlternativeNames = false
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                url("https://pokeapi.co/api/v2/")
            }
        }
    }
    single { ApiService(get()) }
    single { PokemonDataProviders(get()) }
}
