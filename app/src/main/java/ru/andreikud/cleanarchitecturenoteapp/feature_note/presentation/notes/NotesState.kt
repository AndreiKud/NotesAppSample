package ru.andreikud.cleanarchitecturenoteapp.feature_note.presentation.notes

import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.model.Note
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
