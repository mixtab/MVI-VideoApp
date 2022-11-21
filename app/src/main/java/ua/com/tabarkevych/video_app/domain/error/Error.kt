package ua.com.tabarkevych.video_app.domain.error


sealed class Error(val msg: String) {

    class NetworkConnectionError(msg: String) : Error(msg)

    class SomethingWentWrongError(msg: String) : Error(msg)

}