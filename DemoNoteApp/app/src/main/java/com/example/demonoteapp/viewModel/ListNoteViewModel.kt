package com.example.demonoteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.demonoteapp.database.Note
import com.example.demonoteapp.repository.NoteRepository

class ListNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository
    val listAllNotes: LiveData<List<Note>>

    init {
        noteRepository = NoteRepository(application)
        listAllNotes = noteRepository.findBySearchText()
    }
}