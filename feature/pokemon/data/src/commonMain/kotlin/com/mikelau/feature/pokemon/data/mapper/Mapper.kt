package com.mikelau.feature.pokemon.data.mapper

import com.mikelau.core.network.model.PokemonListResponse
import com.mikelau.feature.pokemon.domain.model.Pokemon

fun PokemonListResponse.toDomainPokemonList(): List<Pokemon> {
    return this.results.map {
        Pokemon(id = it.getId(), name = it.name)
    }
}