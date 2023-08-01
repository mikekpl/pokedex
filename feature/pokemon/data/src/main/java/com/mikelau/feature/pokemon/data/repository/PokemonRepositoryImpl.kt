package com.mikelau.feature.pokemon.data.repository

import com.mikelau.core.network.dataproviders.PokemonDataProviders
import com.mikelau.feature.pokemon.data.mapper.toDomainPokemonList
import com.mikelau.feature.pokemon.domain.model.Pokemon
import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonDataProviders: PokemonDataProviders) : PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        return pokemonDataProviders.getPokemonList().toDomainPokemonList()
    }

}