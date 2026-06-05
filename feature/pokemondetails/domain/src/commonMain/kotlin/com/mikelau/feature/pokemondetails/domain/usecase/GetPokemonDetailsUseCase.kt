package com.mikelau.feature.pokemondetails.domain.usecase

import com.mikelau.core.common.UiEvents
import com.mikelau.feature.pokemondetails.domain.model.PokemonDetails
import com.mikelau.feature.pokemondetails.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetPokemonDetailsUseCase(private val pokemonDetailsRepository: PokemonDetailsRepository) {

    operator fun invoke(id: String): Flow<UiEvents<PokemonDetails>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(pokemonDetailsRepository.getPokemonDetails(id)))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
