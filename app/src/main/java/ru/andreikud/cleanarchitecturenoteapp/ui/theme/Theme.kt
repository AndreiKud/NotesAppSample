package ru.andreikud.cleanarchitecturenoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    onBackground = Color.White,
    background = DarkThemeColors.DarkGray,
    surface = DarkThemeColors.LightBlue,
    onSurface = DarkThemeColors.DarkGray
)

private val LightColorPalette = lightColors(
)

@Composable
fun CleanArchitectureNoteAppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}