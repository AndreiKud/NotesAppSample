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
            NotesBackgroundColors.RedOrange,
            NotesBackgroundColors.LightGreen,
            NotesBackgroundColors.Violet,
            NotesBackgroundColors.BabyBlue,
            NotesBackgroundColors.RedPink
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)