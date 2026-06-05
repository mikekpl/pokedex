package com.mikelau.pokedex

import androidx.compose.ui.window.ComposeUIViewController
import com.mikelau.pokedex.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        if (org.koin.core.context.GlobalContext.getOrNull() == null) {
            startKoin {
                modules(appModule)
            }
        }
    }
) {
    App()
}
