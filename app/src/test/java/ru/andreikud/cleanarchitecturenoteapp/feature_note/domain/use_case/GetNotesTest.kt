package ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.use_case

import androidx.compose.ui.graphics.toArgb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.andreikud.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.Note
import ru.andreikud.cleanarchitecturenoteapp.ui.theme.NotesBackgroundColors
import java.util.*
import com.google.common.truth.Truth.assertThat
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.util.OrderType


private fun <T> List<T>.runPairs(op: (T, T) -> Unit) {
    for (i in 0..(size - 2)) {
        op(get(i), get(i + 1))
    }
}

@ExperimentalStdlibApi
class GetNotesTest {

    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository

    private val symbols = "abcdefghijklmnopqrstuvwxyz "

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        populateWithNotes()

        getNotes = GetNotes(fakeNoteRepository)
    }

    private fun populateWithNotes() {
        val colors = NotesBackgroundColors.values()
        val notesToInsert = mutableListOf<Note>()
        repeat(10) {
            val randomTitle = randomText(5, 10)
            val randomContent = randomText(20, 40)
            val randomColor = colors.random().color.toArgb()

            val note = Note(
                title = randomTitle,
                content = randomContent,
                color = randomColor,
                timestamp = System.currentTimeMillis(),
            )
            notesToInsert.add(note)
        }

        runBlocking {
            notesToInsert.shuffled().forEach { note ->
                fakeNoteRepository.insertNote(note)
            }
        }
    }

    private fun randomText(minLength: Int, maxLength: Int): String {
        require(minLength <= maxLength)
        val length = Random().nextInt(maxLength - minLength) + minLength
        val arr = buildList(length) {
            add(symbols.random())
        }
        return arr.joinToString()
    }

    @Test
    fun notesOrder_OrderByTitle_Descending() = runBlocking {
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        notes.runPairs { firstNote, secondNote ->
            assertThat(firstNote.title).isAtLeast(secondNote.title)
        }
    }

    @Test
    fun notesOrder_OrderByTitle_Ascending() = runBlocking {
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        notes.runPairs { firstNote, secondNote ->
            assertThat(firstNote.title).isAtMost(secondNote.title)
        }
    }

    @Test
    fun notesOrder_OrderByTimestamp_Descending() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()
        notes.runPairs { firstNote, secondNote ->
            assertThat(firstNote.timestamp).isAtLeast(secondNote.timestamp)
        }
    }

    @Test
    fun notesOrder_OrderByTimestamp_Ascending() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()
        notes.runPairs { firstNote, secondNote ->
            assertThat(firstNote.timestamp).isAtMost(secondNote.timestamp)
        }
    }


    @Test
    fun notesOrder_OrderByColor_Descending() = runBlocking {
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()
        notes.runPairs { firstNote, secondNote ->
            assertThat(firstNote.color).isAtLeast(secondNote.color)
        }
    }

    @Test
    fun notesOrder_OrderByColor_Ascending() = runBlocking {
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()
        notes.runPairs { firstNote, secondNote ->
            assertThat(firstNote.color).isAtMost(secondNote.color)
        }
    }

}