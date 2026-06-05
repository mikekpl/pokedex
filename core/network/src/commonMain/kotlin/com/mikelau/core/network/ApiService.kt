package com.mikelau.core.network

import com.mikelau.core.network.model.PokemonDetailsDto
import com.mikelau.core.network.model.PokemonListResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ApiService(private val client: HttpClient) {

    suspend fun getPokemonList(): PokemonListResponse =
        client.get("pokemon/?offset=0&limit=2000").body()

    suspend fun getPokemonDetails(id: String): PokemonDetailsDto =
        client.get("pokemon/$id").body()

}
