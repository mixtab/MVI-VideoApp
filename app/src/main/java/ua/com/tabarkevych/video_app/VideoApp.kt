package ua.com.tabarkevych.video_app

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import ua.com.tabarkevych.video_app.enums.ThemeMode

@HiltAndroidApp
class VideoApp : Application(){

    override fun onCreate() {
        super.onCreate()
        initTheme()
    }
    private fun initTheme() {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val modeOrdinal = preferences.getInt("THEME_MODE", ThemeMode.FOLLOW_SYSTEM.ordinal)
        if (modeOrdinal == ThemeMode.FOLLOW_SYSTEM.ordinal) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        } else if (modeOrdinal == ThemeMode.DAY.ordinal) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else if (modeOrdinal == ThemeMode.NIGHT.ordinal) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}