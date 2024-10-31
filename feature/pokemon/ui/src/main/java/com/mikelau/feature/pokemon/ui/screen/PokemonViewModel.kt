package com.mikelau.feature.pokemon.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikelau.core.common.UiEvents
import com.mikelau.core.common.utils.isNumeric
import com.mikelau.feature.pokemon.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _pokemonList = mutableStateOf(PokemonStateHolder())
    val pokemonList: State<PokemonStateHolder> get() = _pokemonList

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    init {
        viewModelScope.launch {
            _query.debounce(100).collectLatest {
                getPokemonList()
            }
        }
    }

    fun setQuery(s: String) {
        _query.value = s
    }

    fun getPokemonList() = viewModelScope.launch {
        if (getPokemonListUseCase != null) {
            getPokemonListUseCase().onEach {
                when (it) {
                    is UiEvents.Loading -> {
                        _pokemonList.value = PokemonStateHolder(isLoading = true)
                    }

                    is UiEvents.Error -> {
                        _pokemonList.value = PokemonStateHolder(error = it.message.toString())
                    }

                    is UiEvents.Success -> {
                        if (isNumeric(query.value)) {
                            _pokemonList.value =
                                PokemonStateHolder(data = it.data?.filter { filter ->
                                    filter.id.contains(_query.value)
                                })
                        } else if (_query.value.isNotBlank()) {
                            _pokemonList.value =
                                PokemonStateHolder(data = it.data?.filter { filter ->
                                    filter.name.contains(_query.value)
                                })
                        } else {
                            _pokemonList.value = PokemonStateHolder(data = it.data)
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}