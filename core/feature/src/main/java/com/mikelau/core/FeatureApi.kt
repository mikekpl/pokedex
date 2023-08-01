package com.mikelau.core

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )

}