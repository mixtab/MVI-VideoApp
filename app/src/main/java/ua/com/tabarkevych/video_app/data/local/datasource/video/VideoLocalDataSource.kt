package ua.com.tabarkevych.video_app.data.local.datasource.video

import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity

interface VideoLocalDataSource {

    suspend fun addVideo(video: VideoEntity)

    suspend fun addVideos(videos: List<VideoEntity>)

    suspend fun getVideos(): List<VideoEntity>

    suspend fun getVideoInfo(id: String): VideoEntity

    suspend fun deleteAllVideo(id: String)

    suspend fun deleteAllVideos()

}