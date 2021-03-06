package ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.use_case

import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.Note
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException(EXCEPTION_BLANK_TITLE)
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException(EXCEPTION_BLANK_CONTENT)
        }
        repository.insertNote(note)
    }

    companion object {
        const val EXCEPTION_BLANK_TITLE = "The title of the note can't be empty."
        const val EXCEPTION_BLANK_CONTENT = "The content of the note can't be empty."
    }
}