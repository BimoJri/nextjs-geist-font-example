package com.example.sakuhijau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    primary = Color(0xFF67C16D),
                    secondary = Color(0xFFB2E3C0),
                    background = Color.White
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "registerName"
                    ) {
                        composable("registerName") { RegisterNameScreen(navController) }
                        composable("registerPhoto") { RegisterPhotoScreen(navController) }
                        composable("registerOtp") { RegisterOtpScreen(navController) }
                        composable("registerEmail") { RegisterEmailScreen(navController) }
                        composable("picture") { PictureScreen(navController) }
                        composable("photoGallery") { PhotoGalleryScreen(navController) }
                    }
                }
            }
        }
    }
}
