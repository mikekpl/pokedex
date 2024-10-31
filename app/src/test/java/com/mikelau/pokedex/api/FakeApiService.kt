package com.mikelau.pokedex.api

import com.mikelau.core.network.ApiService
import com.mikelau.core.network.model.PokemonDetailsDto
import com.mikelau.core.network.model.PokemonDto
import com.mikelau.core.network.model.PokemonListResponse
import com.mikelau.pokedex.JsonProvider
import java.lang.Exception
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiService {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getPokemonList(): PokemonListResponse {
        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: PokemonListResponse = JsonProvider.objectFromJsonFileWithType(POKEMON_LIST_JSON)

        if (wrongResponse) return fakeResponse.apply {
            results = listOf(PokemonDto("", ""))
        }

        return fakeResponse
    }

    override suspend fun getPokemonDetails(id: String): PokemonDetailsDto {
        TODO("Not yet implemented")
    }

    companion object {
        private const val POKEMON_LIST_JSON = "/json/pokemon-list.json"
    }
}