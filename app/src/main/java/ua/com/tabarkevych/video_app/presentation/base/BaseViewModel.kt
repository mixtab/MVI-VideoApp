package ua.com.tabarkevych.video_app.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import ua.com.tabarkevych.video_app.domain.use_case.base.EmptyParams
import ua.com.tabarkevych.video_app.domain.use_case.base.FlowUseCase
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import ua.com.tabarkevych.video_app.domain.use_case.base.UseCase

open class BaseViewModel : ViewModel() {

    private val noInternetConnectionUseCases = mutableListOf<suspend () -> Unit>()

    private val _noInternetConnectionLiveData = MutableLiveData(false)
    val noInternetConnectionLiveData: LiveData<Boolean>
        get() = _noInternetConnectionLiveData

    suspend fun <P : EmptyParams, R : Any> executeUseCase(
        useCase: UseCase<P, R>,
        params: P
    ): Result<R> {
        return when (val result = useCase.execute(params)) {
            is Result.Error -> {
                when (result.error) {
                    is ua.com.tabarkevych.video_app.domain.error.Error.NetworkConnectionError -> {
                        noInternetConnectionUseCases.add { executeUseCase(useCase, params) }
                        _noInternetConnectionLiveData.postValue(true)
                        result
                    }
                    else -> result
                }
            }
            else -> result
        }
    }

    fun <P : EmptyParams, R : Any> executeUseCase(
        useCase: FlowUseCase<P, R>,
        params: P
    ): Flow<Result<R>> {
        return useCase.execute(params).onEach {
            if (it is Result.Error) {
                if (it.error is ua.com.tabarkevych.video_app.domain.error.Error.NetworkConnectionError) {
                    _noInternetConnectionLiveData.postValue(true)
                }
            }
        }
    }

    fun executeNotLoadedUseCases() {
        val bufferedUseCases = noInternetConnectionUseCases.toImmutableList()
        viewModelScope.launch {
            bufferedUseCases.forEach { useCase ->
                noInternetConnectionUseCases.removeFirstOrNull()
                useCase()
            }
        }
    }
}