package com.mikelau.feature.pokemon.domain.usecase

import com.mikelau.core.common.UiEvents
import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {

    operator fun invoke() = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(pokemonRepository.getPokemonList()))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}
