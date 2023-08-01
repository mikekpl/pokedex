package com.mikelau.feature.pokemondetails.data.repository

import com.mikelau.core.network.dataproviders.PokemonDataProviders
import com.mikelau.feature.pokemondetails.data.mapper.toDomain
import com.mikelau.feature.pokemondetails.domain.model.PokemonDetails
import com.mikelau.feature.pokemondetails.domain.repository.PokemonDetailsRepository
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(private val pokemonDataProviders: PokemonDataProviders) :
    PokemonDetailsRepository {
    override suspend fun getPokemonDetails(id: String): PokemonDetails {
        return pokemonDataProviders.getPokemonDetails(id).toDomain()
    }
}