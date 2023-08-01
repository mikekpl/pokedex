package com.mikelau.feature.pokemon.domain.repository

import com.mikelau.feature.pokemon.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList(): List<Pokemon>

}