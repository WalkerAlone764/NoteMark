package com.example.notemark.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.presentation.login.LoginScreenRoot
import com.example.auth.presentation.registration.RegistrationScreenRoot
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
            LandingScreenRoot(
                navigateToRegistration = {
                    navController.navigate(Routes.RegistrationScreen) {
                        popUpTo(Routes.LandingScreen) {
                            inclusive = true
                        }
                    }
                },
                navigateToLogin = {
                    navController.navigate(Routes.LoginScreen) {
                        popUpTo(Routes.LandingScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Routes.RegistrationScreen> {
            RegistrationScreenRoot(
                onSuccessfullyRegistration = {
                    navController.navigate(Routes.LoginScreen) {
                        popUpTo(Routes.RegistrationScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Routes.LoginScreen> {
            LoginScreenRoot(
                onSuccessfullyLogin = {
                    navController.navigate(Routes.HomeScreen) {
                        popUpTo(Routes.LoginScreen) {
                            inclusive = true
                        }
                    }
                },
                onClickDoNotHaveAccount = {
                    navController.navigate(Routes.RegistrationScreen) {
                        popUpTo(Routes.LoginScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Routes.HomeScreen> {

        }

    }
}