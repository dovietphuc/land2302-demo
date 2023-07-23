package com.example.demonoteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demonoteapp.database.Note
import com.example.demonoteapp.repository.NoteRepository
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository

    init {
        noteRepository = NoteRepository(application)
    }

    suspend fun insert(note: Note){
        noteRepository.insert(note)
    }
}