package ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.andreikud.cleanarchitecturenoteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            NotesBackgroundColors.RED_ORANGE.color,
            NotesBackgroundColors.LIGHT_GREEN.color,
            NotesBackgroundColors.VIOLET.color,
            NotesBackgroundColors.BABY_BLUE.color,
            NotesBackgroundColors.RED_PINK.color,
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)