package ua.com.tabarkevych.video_app.data.repository

import ua.com.tabarkevych.video_app.data.local.datasource.video.VideoLocalDataSource
import ua.com.tabarkevych.video_app.data.mapper.model.toDomain
import ua.com.tabarkevych.video_app.data.mapper.model.toDomains
import ua.com.tabarkevych.video_app.data.mapper.model.toEntity
import ua.com.tabarkevych.video_app.domain.model.Video
import ua.com.tabarkevych.video_app.domain.repository.VideoRepository
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import ua.com.tabarkevych.video_app.extensions.result
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoLocalDataSource: VideoLocalDataSource
):VideoRepository {

    override suspend fun addVideo(video: Video): Result<Unit> = result {
        videoLocalDataSource.addVideo(video.toEntity())
    }

    override suspend fun addVideos(videos: List<Video>): Result<Unit> = result {
        videoLocalDataSource.addVideos(videos.toEntity())
    }

    override suspend fun getVideos(): Result<List<Video>> = result {
        videoLocalDataSource.getVideos().toDomains()
    }

    override suspend fun getVideoInfo(id: String):Result<Video>  = result {
        videoLocalDataSource.getVideoInfo(id).toDomain()
    }

    override suspend fun deleteVideo(id: String): Result<Unit> = result {
        videoLocalDataSource.deleteAllVideo(id)
    }

    override suspend fun deleteAllVideos(): Result<Unit> = result {
      videoLocalDataSource.deleteAllVideos()
    }
}