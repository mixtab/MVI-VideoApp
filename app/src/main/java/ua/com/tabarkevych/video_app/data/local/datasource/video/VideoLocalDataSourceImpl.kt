package ua.com.tabarkevych.video_app.data.local.datasource.video

import kotlinx.coroutines.flow.Flow
import ua.com.tabarkevych.video_app.data.local.database.dao.video.VideoDao
import ua.com.tabarkevych.video_app.data.local.database.entity.VideoEntity
import javax.inject.Inject

class VideoLocalDataSourceImpl @Inject constructor(
    private val videoDao: VideoDao
) : VideoLocalDataSource {


    override suspend fun addVideo(video: VideoEntity) {
        videoDao.insertData(video)
    }

    override suspend fun addVideos(videos: List<VideoEntity>) {
        videoDao.insertData(videos)
    }

    override suspend fun getVideos(): List<VideoEntity> {
       return videoDao.getAll()
    }

    override suspend fun getVideoInfo(id: String): VideoEntity {
       return videoDao.getById(id)
    }

    override suspend fun deleteAllVideo(id: String) {
        videoDao.deleteById(id)
    }

    override suspend fun deleteAllVideos() {
       videoDao.deleteAll()
    }

}