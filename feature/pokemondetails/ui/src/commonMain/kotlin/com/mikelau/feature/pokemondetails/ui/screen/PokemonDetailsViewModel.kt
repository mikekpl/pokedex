package com.mikelau.feature.pokemondetails.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikelau.core.common.UiEvents
import com.mikelau.feature.pokemondetails.domain.usecase.GetPokemonDetailsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
) : ViewModel() {
    val pokemonDetails: State<PokemonDetailsStateHolder>
        field = mutableStateOf(PokemonDetailsStateHolder())

    fun getPokemonDetails(id: String) {
        getPokemonDetailsUseCase(id).onEach {
            when (it) {
                is UiEvents.Loading -> {
                    pokemonDetails.value = PokemonDetailsStateHolder(isLoading = true)
                }
                is UiEvents.Error -> {
                    pokemonDetails.value = PokemonDetailsStateHolder(error = it.message.toString())
                }
                is UiEvents.Success -> {
                    pokemonDetails.value = PokemonDetailsStateHolder(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}
