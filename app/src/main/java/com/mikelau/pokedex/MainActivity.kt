package com.mikelau.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mikelau.core.common.utils.ColorBackground
import com.mikelau.pokedex.navigation.AppNavGraph
import com.mikelau.pokedex.navigation.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val navController = rememberNavController()
            App(navHostController = navController, navigationProvider)
        }
    }
}

@Composable
fun App(navHostController: NavHostController, navigationProvider: NavigationProvider) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = ColorBackground)

    Surface(
        modifier = Modifier.background(ColorBackground).fillMaxSize(),
        color = ColorBackground
    ) {
        AppNavGraph(navController = navHostController, navigationProvider = navigationProvider)
    }
}