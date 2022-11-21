package ua.com.tabarkevych.video_app.presentation.videos_list

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.com.tabarkevych.video_app.domain.use_case.base.EmptyParams
import ua.com.tabarkevych.video_app.domain.use_case.videos.AddVideoUseCase
import ua.com.tabarkevych.video_app.domain.use_case.videos.GetVideosUseCase
import ua.com.tabarkevych.video_app.domain.use_case.videos.RemoveVideoUseCase
import ua.com.tabarkevych.video_app.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val addVideoUseCase: AddVideoUseCase,
    private val removeVideoUseCase: RemoveVideoUseCase
) : BaseViewModel() {


    var state by mutableStateOf(VideosState())
        private set

    init {
        getVideoListState()
    }

    private fun getVideoListState() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = executeUseCase(
                getVideosUseCase,
                EmptyParams()
            )) {
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Error -> {
                    state = state.copy(
                        videosList = null,
                        isLoading = false,
                        error = "Cant Load List"
                    )
                }
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Success -> {
                    state = state.copy(
                        videosList = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    fun addVideo(image: Uri?, title: String, description: String) {
        if (image == null) return
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (executeUseCase(
                addVideoUseCase,
                AddVideoUseCase.AddVideInfoParams(image, title, description)
            )) {
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Error -> {
                    state = state.copy(
                        videosList = null,
                        isLoading = false,
                        error = "Cant Load List"
                    )
                }
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Success -> {
                    getVideoListState()
                }
            }
        }
    }

    fun removeVideo(id: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (executeUseCase(
                removeVideoUseCase,
                RemoveVideoUseCase.RemoveVideInfoParams(id)
            )) {
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Error -> {
                    state = state.copy(
                        videosList = null,
                        isLoading = false,
                        error = "Cant Load List"
                    )
                }
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Success -> {
                    getVideoListState()
                }
            }
        }
    }

}