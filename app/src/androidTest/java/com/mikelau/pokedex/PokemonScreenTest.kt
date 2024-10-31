package com.mikelau.pokedex

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mikelau.core.common.UiEvents
import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import com.mikelau.feature.pokemon.domain.usecase.GetPokemonListUseCase
import com.mikelau.feature.pokemon.ui.screen.PokemonScreen
import com.mikelau.feature.pokemon.ui.screen.PokemonViewModel
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class PokemonScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var pokemonViewModel: PokemonViewModel

    @Mock
    private lateinit var mockPokemonRepository: PokemonRepository

    @Mock
    lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun myTempTest() {

        // Mock the flow to emit loading and success events
        val flow = flow {
            emit(UiEvents.Loading())
            emit(UiEvents.Success(mockPokemonRepository.getPokemonList()))
        }

        // Initialize the ViewModel
        pokemonViewModel = PokemonViewModel(getPokemonListUseCase)

        // Arrange the mock NavController
        val mockNavController = Mockito.mock(NavController::class.java)

        // Start the app
        composeTestRule.setContent {
            PokemonScreen(
                pokemonViewModel,
                mockNavController
            )
        }

        // Perform actions and assertions
        composeTestRule.onNodeWithText("Pokédex").assertIsDisplayed()
    }
}
