package com.example.notemark

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.notemark.navigation.SetupNavigation
import org.koin.android.ext.android.inject
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingAuth
            }
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.BLACK, darkScrim = Color.BLACK
            ), navigationBarStyle = SystemBarStyle.light(
                scrim = Color.BLACK, darkScrim = Color.BLACK
            )
        )

        setContent {
            KoinContext {
                NoteMarkTheme {
                    val navController = rememberNavController()
                    SetupNavigation(
                        navController = navController, isLoggedIn = viewModel.state.isLoggedIn
                    )
                }
            }
        }
    }
}