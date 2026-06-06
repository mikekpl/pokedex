package com.mikelau.feature.pokemon.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikelau.core.common.UiEvents
import com.mikelau.core.common.utils.isNumeric
import com.mikelau.feature.pokemon.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {

    val pokemonList: State<PokemonStateHolder>
        field = mutableStateOf(PokemonStateHolder())

    val query: StateFlow<String>
        field: MutableStateFlow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch {
            query.debounce(100).collectLatest {
                getPokemonList()
            }
        }
    }

    fun setQuery(s: String) {
        query.value = s
    }

    fun getPokemonList() = viewModelScope.launch {
        getPokemonListUseCase().onEach {
            when (it) {
                is UiEvents.Loading -> {
                    pokemonList.value = PokemonStateHolder(isLoading = true)
                }
                is UiEvents.Error -> {
                    pokemonList.value = PokemonStateHolder(error = it.message.toString())
                }
                is UiEvents.Success -> {
                    if (isNumeric(query.value)) {
                        pokemonList.value = PokemonStateHolder(data = it.data?.filter {
                                filter -> filter.id.contains(query.value)
                        })
                    } else if (query.value.isNotBlank()) {
                        pokemonList.value = PokemonStateHolder(data = it.data?.filter {
                                filter -> filter.name.contains(query.value.lowercase())
                        })
                    } else {
                        pokemonList.value = PokemonStateHolder(data = it.data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
