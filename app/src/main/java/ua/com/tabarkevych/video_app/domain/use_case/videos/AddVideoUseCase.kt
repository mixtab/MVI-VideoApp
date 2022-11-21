package ua.com.tabarkevych.video_app.domain.use_case.videos

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import ua.com.tabarkevych.video_app.domain.model.Video
import ua.com.tabarkevych.video_app.domain.repository.VideoRepository
import ua.com.tabarkevych.video_app.domain.use_case.base.EmptyParams
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import ua.com.tabarkevych.video_app.domain.use_case.base.UseCase
import ua.com.tabarkevych.video_app.presentation.utils.MediaUtils
import javax.inject.Inject

class AddVideoUseCase @Inject constructor(
    private val videoRepository: VideoRepository,
    @ApplicationContext val context: Context,
) : UseCase<AddVideoUseCase.AddVideInfoParams, Unit>() {

    override suspend fun run(params: AddVideInfoParams): Result<Unit> {
        val video = Video(params.title,MediaUtils.copyFileToInternalStorage(context, params.image)?:"",params.title,params.description)
        return videoRepository.addVideo(video)
    }

    class AddVideInfoParams(
        val image: Uri,
        val title:String,
        val description:String
    ) : EmptyParams()

}