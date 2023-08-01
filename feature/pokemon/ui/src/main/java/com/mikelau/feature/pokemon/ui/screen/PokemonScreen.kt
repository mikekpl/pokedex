package com.mikelau.feature.pokemon.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.mikelau.core.common.utils.ColorBackground
import com.mikelau.core.common.utils.ColorIconSearchBackground
import com.mikelau.core.common.utils.ColorIconSearchBorder
import com.mikelau.core.common.utils.ColorLazyGridItem
import com.mikelau.core.common.utils.ColorTextFieldContainerDefault
import com.mikelau.core.common.utils.ColorTextFieldText
import com.mikelau.core.common.utils.ColorTextItems
import com.mikelau.core.common.utils.ColorTextTitle
import com.mikelau.core.common.utils.getEmptyList
import com.mikelau.core.common.utils.getErrorList
import com.mikelau.core.common.utils.getPokemonImage
import com.mikelau.core.common.utils.isNumeric
import com.mikelau.core.common.utils.titleCase

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PokemonScreen(viewModel: PokemonViewModel, navController: NavController) {

    val result = viewModel.pokemonList.value
    val query = viewModel.query.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .background(color = ColorBackground)
            .padding(top = 8.dp)
            .noRippleClickable {
                keyboardController?.hide()
            },
        topBar = {
            Column(
                modifier = Modifier
                    .background(color = ColorBackground)
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Text(
                    text = "Pokédex",
                    color = ColorTextTitle,
                    modifier = Modifier
                        .padding(top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    style = typography.headlineMedium
                )
                Text(
                    text = "Search for a Pokémon by name or using its National Pokédex number.",
                    color = ColorTextTitle,
                    modifier = Modifier
                        .padding(top = 4.dp),
                    style = typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .weight(4f),
                        value = query.value,
                        onValueChange = {
                            viewModel.setQuery(it)
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = ColorTextFieldText,
                            focusedContainerColor = ColorTextFieldContainerDefault,
                            unfocusedContainerColor = ColorTextFieldContainerDefault,
                            disabledContainerColor = ColorTextFieldContainerDefault,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        placeholder = { Text(text = "Name or number") }
                    )
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                            .clickable {
                                if (isNumeric(query.value)) {
                                    navController.navigate("pokemon_details/${query.value}")
                                } else if (query.value.isBlank()) {
                                    navController.navigate("pokemon_details/${0}")
                                } else {
                                    val pokemonNumber =
                                        result.data?.indexOfFirst { it.name == query.value } ?: 0
                                    val queryPokemonNumber = pokemonNumber + 1
                                    navController.navigate("pokemon_details/${queryPokemonNumber}")
                                }
                            },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = ColorIconSearchBackground)
                                .border(2.dp, ColorIconSearchBorder)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                imageVector = Icons.Default.SyncAlt,
                                tint = ColorBackground,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }) { screen ->
        Log.e("TAG", "PokemonScreen: $screen")

        if (result.isLoading) {
            Box(modifier = Modifier
                .background(color = ColorBackground).fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        if (result.error.isNotBlank()) {
            Box(modifier = Modifier
                .background(color = ColorBackground).fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .padding(
                                top = 8.dp,
                                bottom = 8.dp,
                                start = 20.dp,
                                end = 20.dp
                            )
                            .fillMaxWidth()
                            .height(130.dp),
                        alignment = Alignment.Center,
                        model = getErrorList(),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Oops! There was a problem\nPlease come back again later.",
                        color = ColorTextItems,
                        textAlign = TextAlign.Center,
                        style = typography.titleMedium
                    )
                }
            }
        }
        result.data?.let {
            if (it.isEmpty()) {
                Box(modifier = Modifier.background(color = ColorBackground).fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .padding(
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    start = 20.dp,
                                    end = 20.dp
                                )
                                .fillMaxWidth()
                                .height(130.dp),
                            alignment = Alignment.Center,
                            model = getEmptyList(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Pokemon Not Found",
                            color = ColorTextItems,
                            textAlign = TextAlign.Center,
                            style = typography.titleMedium
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier
                        .background(color = ColorBackground)
                        .padding(top = 180.dp, start = 16.dp, end = 16.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        itemsIndexed(it) { index, it ->
                            val pokemonNumber = it.id
                            Card(
                                modifier = Modifier
                                    .height(212.dp)
                                    .padding(8.dp)
                                    .clickable {
                                        navController.navigate("pokemon_details/${pokemonNumber}")
                                    },
                                shape = RoundedCornerShape(16.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .background(ColorLazyGridItem[index % ColorLazyGridItem.size])
                                ) {
                                    SubcomposeAsyncImage(
                                        modifier = Modifier
                                            .padding(
                                                top = 8.dp,
                                                bottom = 8.dp,
                                                start = 20.dp,
                                                end = 20.dp
                                            )
                                            .fillMaxWidth()
                                            .height(130.dp),
                                        alignment = Alignment.Center,
                                        model = getPokemonImage(pokemonNumber),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        loading = { LoadingAnimation() }
                                    )

                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                start = 12.dp,
                                                end = 12.dp
                                            ),
                                        text = it.name.titleCase(),
                                        maxLines = 1,
                                        color = ColorTextItems,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        style = typography.bodyLarge
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                start = 12.dp,
                                                end = 12.dp
                                            ),
                                        text = pokemonNumber.padStart(3, '0'),
                                        maxLines = 1,
                                        color = ColorTextItems,
                                        textAlign = TextAlign.Center,
                                        style = typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}