package ua.com.tabarkevych.video_app.data.error.exeptions

import ua.com.tabarkevych.video_app.data.remote.responcebody.ErrorResponseBody
import java.io.IOException

object Exception {

    class NetworkConnectionException : IOException()

    class NoResponseContentException : IOException()

    open class NetworkRequestException(val error: ErrorResponseBody) : IOException()

    open class LocalRequestException(val originalThrowable: Throwable?) : IOException()

}