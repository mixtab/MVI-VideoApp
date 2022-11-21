package ua.com.tabarkevych.video_app.presentation.video_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.com.tabarkevych.video_app.domain.use_case.videos.GetVideoInfoUseCase
import ua.com.tabarkevych.video_app.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class VideoInfoViewModel @Inject constructor(
    private val getVideoInfoUseCase: GetVideoInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    var state by mutableStateOf(VideoInfoState())
        private set

    init {
        savedStateHandle.get<String>("videoId")?.let {
            getVideoInfoUseCase(it)
        }
    }

    private fun getVideoInfoUseCase(id:String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = executeUseCase(
                getVideoInfoUseCase,
                GetVideoInfoUseCase.GetVideInfoParams(id)
            )) {
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Error -> {
                    state = state.copy(
                        video = null,
                        isLoading = false,
                        error = "Cant Load List"
                    )
                }
                is ua.com.tabarkevych.video_app.domain.use_case.base.Result.Success -> {
                    state = state.copy(
                        video = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

}