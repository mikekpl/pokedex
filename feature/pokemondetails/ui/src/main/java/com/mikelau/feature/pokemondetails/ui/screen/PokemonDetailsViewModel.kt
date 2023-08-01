package com.mikelau.feature.pokemondetails.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikelau.core.common.UiEvents
import com.mikelau.feature.pokemondetails.domain.usecase.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _pokemonDetails = mutableStateOf(PokemonDetailsStateHolder())
    val pokemonDetails: State<PokemonDetailsStateHolder> get() = _pokemonDetails

    init {
        savedStateHandle.getLiveData<String>("id").observeForever {
            it?.let {
                getPokemonDetails(it)
            }
        }
    }

    fun getPokemonDetails(id: String) {
        getPokemonDetailsUseCase(id).onEach {
            when (it) {
                is UiEvents.Loading -> {
                    _pokemonDetails.value = PokemonDetailsStateHolder(isLoading = true)
                }

                is UiEvents.Error -> {
                    _pokemonDetails.value = PokemonDetailsStateHolder(error = it.message.toString())
                }

                is UiEvents.Success -> {
                    _pokemonDetails.value = PokemonDetailsStateHolder(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}