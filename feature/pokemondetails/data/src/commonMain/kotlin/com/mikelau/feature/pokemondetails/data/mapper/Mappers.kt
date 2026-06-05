package com.mikelau.feature.pokemondetails.data.mapper

import com.mikelau.core.network.model.PokemonDetailsDto
import com.mikelau.feature.pokemondetails.domain.model.PokemonDetails

fun PokemonDetailsDto.toDomain(): PokemonDetails {
    return PokemonDetails(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        type = this.types.map { it.type.name }
    )
}