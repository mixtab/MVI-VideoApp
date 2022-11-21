package ua.com.tabarkevych.video_app.domain.use_case.base


sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error(val error: ua.com.tabarkevych.video_app.domain.error.Error) :
        Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}
