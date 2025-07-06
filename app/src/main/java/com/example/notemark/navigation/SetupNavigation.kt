package com.example.notemark.navigation

import android.util.Log
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.auth.presentation.login.LoginScreenRoot
import com.example.auth.presentation.registration.RegistrationScreenRoot
import com.example.landing.presentation.LandingScreenRoot
import com.example.notes.presentation.add.AddNoteRoot
import com.example.notes.presentation.list.NoteListRoot

@Composable
fun SetupNavigation(
    navController: NavHostController,
    isLoggedIn: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Routes.NoteListScreen else Routes.LandingScreen
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
                    navController.navigate(Routes.NoteListScreen) {
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

        composable<Routes.NoteListScreen> {
            NoteListRoot(
                onNavigateToAddNote = {
                    Log.d("TAG", "Navigate To: $it")
                    navController.navigate(Routes.AddNoteScreen(it))
                }
            )
        }

        composable<Routes.AddNoteScreen> {
            val args = it.savedStateHandle.toRoute<Routes.AddNoteScreen>()

            AddNoteRoot()
        }

    }
}