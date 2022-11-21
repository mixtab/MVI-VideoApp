package ua.com.tabarkevych.video_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.com.tabarkevych.video_app.presentation.navigation.Screen
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomComposeTheme
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomThemeManager
import ua.com.tabarkevych.video_app.presentation.video_info.components.VideoInfoScreen
import ua.com.tabarkevych.video_app.presentation.videos_list.components.VideosListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomComposeTheme {
                Surface(color = CustomThemeManager.colors.backgroundPrimary) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.VideosListScreen.destination
                    ) {
                        composable(Screen.VideosListScreen.destination) {
                            VideosListScreen(navController)
                        }
                        composable(Screen.VideoInfoScreen.destination + "/{videoId}") {
                            VideoInfoScreen()
                        }
                    }
                }
            }
        }
    }
}

