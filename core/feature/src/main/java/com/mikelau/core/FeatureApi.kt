package com.mikelau.core

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun interface FeatureApi {
    fun registerGraph(backStack: NavBackStack<NavKey>)
}