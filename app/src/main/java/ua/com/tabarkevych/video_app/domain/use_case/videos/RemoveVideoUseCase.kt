package ua.com.tabarkevych.video_app.domain.use_case.videos

import ua.com.tabarkevych.video_app.domain.repository.VideoRepository
import ua.com.tabarkevych.video_app.domain.use_case.base.EmptyParams
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import ua.com.tabarkevych.video_app.domain.use_case.base.UseCase
import javax.inject.Inject

class RemoveVideoUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : UseCase<RemoveVideoUseCase.RemoveVideInfoParams, Unit>() {

    override suspend fun run(params: RemoveVideInfoParams): Result<Unit> {
        return videoRepository.deleteVideo(params.id)
    }

    class RemoveVideInfoParams(
        val id: String
    ) : EmptyParams()

}