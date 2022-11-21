package ua.com.tabarkevych.video_app.presentation.navigation

sealed class Screen(val destination: String) {
    object VideosListScreen : Screen("videos_list")
    object VideoInfoScreen : Screen("video_info_screen")

}