package ua.com.tabarkevych.video_app.extensions

import android.util.Log
import ua.com.tabarkevych.video_app.BuildConfig

fun loge(tag: String, text: String?, tr: Throwable?) {
    if (!BuildConfig.DEBUG) return
    Log.e(tag, text, tr)
}