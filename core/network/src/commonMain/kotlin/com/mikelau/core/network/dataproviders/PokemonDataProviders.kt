package com.mikelau.core.network.dataproviders

import com.mikelau.core.network.ApiService

class PokemonDataProviders(private val apiService: ApiService) {

    suspend fun getPokemonList() = apiService.getPokemonList()

    suspend fun getPokemonDetails(id: String) = apiService.getPokemonDetails(id)

}
