package ua.com.tabarkevych.video_app.presentation.video_info

import ua.com.tabarkevych.video_app.domain.model.Video

data class VideoInfoState(
    val video:Video? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)