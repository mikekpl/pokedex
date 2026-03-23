package com.mikelau.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mikelau.core.common.utils.ColorBackground
import com.mikelau.pokedex.navigation.AppNavGraph
import com.mikelau.pokedex.navigation.rememberAppNavBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    Surface(
        modifier = Modifier
            .background(ColorBackground)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        color = ColorBackground
    ) {
        val backStack = rememberAppNavBackStack()
        AppNavGraph(backStack = backStack)
    }
}