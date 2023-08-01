package com.mikelau.core.network

import com.mikelau.core.network.model.PokemonDetailsDto
import com.mikelau.core.network.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // https://pokeapi.co/api/v2/pokemon/?offset=0&limit=1010
    // https://pokeapi.co/api/v2/pokemon/1
    // #1010 Official Count of Pokemon as of 07-28-2023
    @GET("pokemon/?offset=0&limit=1010")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: String): PokemonDetailsDto

}