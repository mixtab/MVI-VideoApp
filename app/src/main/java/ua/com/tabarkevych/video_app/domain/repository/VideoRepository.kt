package ua.com.tabarkevych.video_app.domain.repository

import ua.com.tabarkevych.video_app.domain.model.Video
import ua.com.tabarkevych.video_app.domain.use_case.base.Result

interface VideoRepository {

    suspend fun addVideo(video: Video):Result<Unit>

    suspend fun addVideos(videos: List<Video>):Result<Unit>

    suspend fun getVideos():Result<List<Video>>

    suspend fun getVideoInfo(id: String): Result<Video>

    suspend fun deleteVideo(id: String):Result<Unit>

    suspend fun deleteAllVideos():Result<Unit>

}