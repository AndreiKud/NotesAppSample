package ru.andreikud.cleanarchitecturenoteapp.ui.theme

import androidx.compose.ui.graphics.Color

// Dark theme colors.
object DarkThemeColors {
    val DarkGray = Color(0xFF202020)
    val LightBlue = Color(0xFFD7E8DE)
}

// Light theme colors.
object LightThemeColors {
    val DarkGray = Color(0xFFD0D0D0)
    val LightBlue = Color(0xFFD9E8FE)
}

// Notes background colors.
enum class NotesBackgroundColors(colorHex: Long) {
    RED_ORANGE(0xffffab91),
    RED_PINK(0xfff48fb1),
    BABY_BLUE(0xff81deea),
    VIOLET(0xffcf94da),
    LIGHT_GREEN(0xffe7ed9b);

    val color = Color(colorHex)
}
