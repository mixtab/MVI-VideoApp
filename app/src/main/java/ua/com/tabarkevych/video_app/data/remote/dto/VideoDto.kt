package ua.com.tabarkevych.video_app.data.remote.dto

import com.squareup.moshi.Json

data class VideoDto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String
)