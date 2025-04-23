package com.mikelau.feature.pokemondetails.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.SubcomposeAsyncImage
import com.mikelau.core.common.utils.ColorBackground
import com.mikelau.core.common.utils.ColorPastel3
import com.mikelau.core.common.utils.ColorPokemonTypeMap
import com.mikelau.core.common.utils.ColorShiny
import com.mikelau.core.common.utils.ColorTextItems
import com.mikelau.core.common.utils.getMissingNumber
import com.mikelau.core.common.utils.getPokemonImage
import com.mikelau.core.common.utils.getPokemonShinyImage
import com.mikelau.core.common.utils.titleCase

@Composable
fun PokemonDetailsScreen(
    id: String,
    viewModel: PokemonDetailsViewModel,
    navController: NavController
) {

    val result = viewModel.pokemonDetails.value

    Scaffold(modifier = Modifier.background(ColorBackground).fillMaxSize()) { screen ->
        Log.e("TAG", "Pokemon Details Screen: $screen")

        if (result.isLoading) {
            Box(modifier = Modifier.background(ColorBackground).fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        if (result.error.isNotBlank()) {
            Column(
                modifier = Modifier
                    .background(ColorBackground)
                    .padding(top = 32.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        tint = ColorTextItems,
                        contentDescription = null
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Missing No.",
                            color = ColorTextItems,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "???",
                            color = ColorTextItems,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(32.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(ColorPastel3)
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(64.dp),
                            model = getMissingNumber(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            loading = { LoadingAnimation() }
                        )
                    }
                }
            }
        }

        result.data?.let {
            Column(
                modifier = Modifier
                    .background(ColorBackground)
                    .padding(top = 32.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        tint = ColorTextItems,
                        contentDescription = null
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.name.titleCase(),
                            color = ColorTextItems,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.id.toString().padStart(3, '0'),
                            color = ColorTextItems,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(32.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(ColorPastel3)
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(64.dp),
                            model = getPokemonImage(id),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            loading = { LoadingAnimation() }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp)
                ) {
                    for (i in 0 until it.type.size) {
                        val pokemonType = it.type[i] ?: ""
                        Text(
                            modifier = Modifier
                                .background(
                                    ColorPokemonTypeMap[pokemonType]!!,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(top = 8.dp, bottom = 8.dp, start = 32.dp, end = 32.dp),
                            text = pokemonType.uppercase(),
                            color = ColorBackground,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, top = 16.dp),
                    text = "Height: ${it.getMetricHeight()}",
                    color = ColorTextItems,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, top = 4.dp),
                    text = "Weight: ${it.getMetricWeight()}",
                    color = ColorTextItems,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, top = 4.dp),
                    text = "Shiny: ",
                    color = ColorTextItems,
                    style = MaterialTheme.typography.titleMedium
                )
                Card(
                    modifier = Modifier
                        .width(180.dp)
                        .height(120.dp)
                        .padding(start = 32.dp, end = 32.dp, top = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ColorShiny)
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            model = getPokemonShinyImage(id),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            loading = { LoadingAnimation() }
                        )
                    }
                }
            }
        }
    }
}