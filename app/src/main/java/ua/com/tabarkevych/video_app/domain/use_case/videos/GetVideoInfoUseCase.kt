package ua.com.tabarkevych.video_app.domain.use_case.videos

import ua.com.tabarkevych.video_app.domain.model.Video
import ua.com.tabarkevych.video_app.domain.repository.VideoRepository
import ua.com.tabarkevych.video_app.domain.use_case.base.EmptyParams
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import ua.com.tabarkevych.video_app.domain.use_case.base.UseCase
import javax.inject.Inject

class GetVideoInfoUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : UseCase<GetVideoInfoUseCase.GetVideInfoParams, Video>() {

    override suspend fun run(params: GetVideInfoParams): Result<Video> {
        return videoRepository.getVideoInfo(params.id)
    }

    class GetVideInfoParams(
        val id: String
    ) : EmptyParams()

}