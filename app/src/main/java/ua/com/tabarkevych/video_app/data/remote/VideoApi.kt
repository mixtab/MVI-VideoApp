package ua.com.tabarkevych.video_app.data.remote

import retrofit2.http.GET
import ua.com.tabarkevych.video_app.data.remote.dto.VideoDto

interface VideoApi {

    @GET()
    suspend fun getVideos():List<VideoDto>

}