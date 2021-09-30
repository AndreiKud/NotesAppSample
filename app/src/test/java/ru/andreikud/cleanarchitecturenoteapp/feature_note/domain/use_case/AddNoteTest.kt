package ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.use_case

import android.graphics.Color
import org.junit.Assert.*
import org.junit.Test
import ru.andreikud.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.function.ThrowingRunnable
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException

class AddNoteTest {

    private val notesRepository = FakeNoteRepository()
    private val addNote = AddNote(notesRepository)

    @Test
    fun blankTitle_Exception() {
        val note = Note(
            title = "",
            content = "some content",
            timestamp = System.currentTimeMillis(),
            color = Color.RED,
        )

        assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }
    }

    @Test
    fun blankContent_Exception() {
        val note = Note(
            title = "wow title",
            content = "",
            timestamp = System.currentTimeMillis(),
            color = Color.RED,
        )

        assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }
    }

    @Test
    fun validNote_Add() = runBlocking {
        val note = Note(
            title = "wow title",
            content = "some content",
            timestamp = System.currentTimeMillis(),
            color = Color.RED,
        )

        addNote(note)
        val notes = notesRepository.getNotes().first()
        assertThat(note).isIn(notes)
    }
}
