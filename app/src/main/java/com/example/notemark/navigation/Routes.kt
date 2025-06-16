package com.example.notemark.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object LandingScreen: Routes

    @Serializable
    data object RegistrationScreen: Routes

    @Serializable
    data object LoginScreen: Routes

    @Serializable
    data object HomeScreen: Routes
}