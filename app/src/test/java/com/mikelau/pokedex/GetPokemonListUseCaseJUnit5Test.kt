package com.mikelau.pokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mikelau.core.common.UiEvents
import com.mikelau.feature.pokemon.data.mapper.toDomainPokemonList
import com.mikelau.feature.pokemon.domain.model.Pokemon
import com.mikelau.feature.pokemon.domain.repository.PokemonRepository
import com.mikelau.feature.pokemon.domain.usecase.GetPokemonListUseCase
import com.mikelau.feature.pokemon.ui.screen.PokemonViewModel
import com.mikelau.pokedex.api.FakeApiService
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.stubbing.Answer
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@HiltAndroidTest
@Config(sdk = [25], application = HiltTestApplication::class, manifest= Config.NONE)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
@ExtendWith(MockitoExtension::class)
class GetPokemonListUseCaseJUnit5Test {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockPokemonRepository: PokemonRepository

    @Mock
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    private var pokemonList = listOf<Pokemon>()

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    private lateinit var pokemonViewModel: PokemonViewModel

    companion object {

        private val dispatcher = TestCoroutineDispatcher()

        @JvmStatic
        @BeforeAll
        fun setupClass() {
            // Setup for the class
            Dispatchers.setMain(dispatcher)
        }

        @JvmStatic
        @AfterAll
        fun tearDownClass() {
            // Teardown for the class
            Dispatchers.resetMain()
        }
    }

    @BeforeEach
    fun setup() {

        val pokemonList = listOf<Pokemon>() // ... populate pokemonList

        val expectedFlow = flow {
            emit(UiEvents.Loading())
            emit(UiEvents.Success(pokemonList))
        }

        Mockito.`when`(getPokemonListUseCase.invoke()).thenReturn(expectedFlow)
        pokemonViewModel = PokemonViewModel(getPokemonListUseCase)
    }

    @Test
    fun testUseCaseFlowEmission() {
        // Define an Answer to mock flow emission
        val flowAnswer = Answer<Flow<UiEvents<List<Pokemon>>>> { invocation ->
            val flow = flow {
                emit(UiEvents.Loading())
                emit(UiEvents.Success(mockPokemonRepository.getPokemonList()))
            }
            flow
        }

        // Extract the actual Flow<String> from the Answer
        val flowToEmit = flowAnswer.answer(null) // Provide null argument (optional)

        // Configure mock behavior using the Answer
        Mockito.`when`(getPokemonListUseCase.invoke()).thenReturn(flowToEmit)

        runTest {

            pokemonList = fakeApiService.getPokemonList().toDomainPokemonList()

            Mockito.`when`(mockPokemonRepository.getPokemonList()).thenReturn(pokemonList)

            // Use the mock use case in your ViewModel test logic
            pokemonViewModel = PokemonViewModel(getPokemonListUseCase)
            pokemonViewModel.getPokemonList()

            val value = pokemonViewModel.pokemonList.value

            org.junit.jupiter.api.Assertions.assertNotNull(value.data)

        }
    }
}