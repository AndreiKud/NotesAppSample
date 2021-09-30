package ru.andreikud.cleanarchitecturenoteapp.feature_note.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.Note
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class FakeNoteRepository : NoteRepository {

    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> = flow {
        emit(notes)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.find { note -> note.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }
}