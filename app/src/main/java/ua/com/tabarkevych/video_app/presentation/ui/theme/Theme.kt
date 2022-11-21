package ua.com.tabarkevych.video_app.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.*

private val LightThemeColors = CustomColors(
    backgroundPrimary= White,
    backgroundSecondary = Yellow,
    buttonBackgroundColor =  Yellow,
    textSecondary = Black,
    textPrimary = Black
)

private val DarkThemeColors = CustomColors(
    backgroundPrimary= Grey,
    backgroundSecondary = Yellow,
    buttonBackgroundColor =  Blue,
    textSecondary = White,
    textPrimary = White
)

private val LocalColorsProvider = staticCompositionLocalOf {
    LightThemeColors
}

@Composable
private fun CustomLocalProvider(
    colors: CustomColors, content: @Composable () -> Unit
) {
    val colorPalette = remember { colors.copy() }
    colorPalette.updateColors(colors)
    CompositionLocalProvider(LocalColorsProvider provides colorPalette, content = content)

}

private val CustomTheme.colors: Pair<Colors, CustomColors>
    get() = when (this) {
        CustomTheme.DARK -> darkColors() to DarkThemeColors
        CustomTheme.LIGHT -> darkColors() to LightThemeColors
    }

object CustomThemeManager {

    val colors: CustomColors
        @Composable
        get() = LocalColorsProvider.current

    var customTheme by mutableStateOf(CustomTheme.LIGHT)
    fun isSystemDarkTheme(): Boolean {
        return customTheme == CustomTheme.DARK
    }
}

@Composable
fun CustomComposeTheme(
    customTheme: CustomTheme = CustomThemeManager.customTheme,
    content: @Composable () -> Unit
) {
    val (colorPalette, lcColor) = customTheme.colors
    CustomLocalProvider(colors = lcColor) {
        MaterialTheme(
            colors = colorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}
