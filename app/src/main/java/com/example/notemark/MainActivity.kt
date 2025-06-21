package com.example.notemark

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.notemark.navigation.SetupNavigation
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.BLACK,
                darkScrim = Color.BLACK
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.BLACK,
                darkScrim = Color.BLACK
            )
        )

        setContent {
            NoteMarkTheme {
                val navController = rememberNavController()
                SetupNavigation(
                    navController = navController
                )
            }
        }
    }
}