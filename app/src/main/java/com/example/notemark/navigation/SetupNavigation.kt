package com.example.notemark.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.landing.presentation.LandingScreenRoot

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LandingScreen
    ) {
        composable<Routes.LandingScreen> {
            LandingScreenRoot()
        }

    }
}