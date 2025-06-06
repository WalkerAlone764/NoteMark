package com.example.notemark.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.presentation.registration.RegistrationScreenRoot
import com.example.landing.presentation.LandingScreenRoot

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.RegistrationScreen
    ) {
        composable<Routes.LandingScreen> {
            LandingScreenRoot()
        }

        composable<Routes.RegistrationScreen> {
            RegistrationScreenRoot()
        }

    }
}