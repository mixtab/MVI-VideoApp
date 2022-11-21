package ua.com.tabarkevych.video_app.presentation.videos_list

import ua.com.tabarkevych.video_app.domain.model.Video

data class VideosState(
    val videosList: List<Video>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)