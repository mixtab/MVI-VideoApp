package ua.com.tabarkevych.video_app.presentation.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

enum class CustomTheme{
    DARK,LIGHT
}

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF161616)
val Yellow = Color(0xFFFFE500)
val Grey = Color(0xFF747370)
val Blue = Color(0xFF008EFF)


@Stable
class CustomColors(
    backgroundPrimary:Color,
    backgroundSecondary:Color,
    buttonBackgroundColor:Color,
    textSecondary:Color,
    textPrimary:Color
) {

    var backgroundPrimary by mutableStateOf(backgroundPrimary)
    private set

    var backgroundSecondary by mutableStateOf(backgroundSecondary)

        private set
    var buttonBackgroundColor by mutableStateOf(buttonBackgroundColor)
        private set

    var textSecondary by mutableStateOf(textSecondary)
        private set

    var textPrimary by mutableStateOf(textPrimary)
        private set

    fun updateColors(color:CustomColors){
        this.backgroundPrimary = color.backgroundPrimary
        this.backgroundSecondary = color.backgroundSecondary
        this.buttonBackgroundColor = color.buttonBackgroundColor
        this.textSecondary = color.textSecondary
        this.textPrimary = color.textPrimary
    }

    fun copy()= CustomColors(
        backgroundPrimary,backgroundSecondary,buttonBackgroundColor,textSecondary,textPrimary
    )
}