package ru.andreikud.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.andreikud.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import ru.andreikud.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import ru.andreikud.cleanarchitecturenoteapp.feature_note.domain.use_case.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            NoteDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

}