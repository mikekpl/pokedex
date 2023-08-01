package com.mikelau.feature.pokemondetails.domain.repository

import com.mikelau.feature.pokemondetails.domain.model.PokemonDetails

interface PokemonDetailsRepository {

    suspend fun getPokemonDetails(id: String) : PokemonDetails

}