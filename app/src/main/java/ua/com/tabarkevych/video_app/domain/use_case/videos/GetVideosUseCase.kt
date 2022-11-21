package ua.com.tabarkevych.video_app.domain.use_case.videos

import ua.com.tabarkevych.video_app.domain.model.Video
import ua.com.tabarkevych.video_app.domain.repository.VideoRepository
import ua.com.tabarkevych.video_app.domain.use_case.base.EmptyParams
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import ua.com.tabarkevych.video_app.domain.use_case.base.UseCase
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : UseCase<EmptyParams, List<Video>>() {

    override suspend fun run(params: EmptyParams): Result<List<Video>> {
        return videoRepository.getVideos()
    }
}