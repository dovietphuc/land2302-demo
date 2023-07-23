package com.example.demonoteapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.demonoteapp.database.Note
import com.example.demonoteapp.database.NoteDao
import com.example.demonoteapp.database.NoteRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(application: Application) {
    private val noteDao: NoteDao

    init {
        val db = NoteRoomDatabase.getInstance(application)
        noteDao = db.noteDao()
    }

    suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    fun findBySearchText(searchText: String = ""): LiveData<List<Note>> {
        return noteDao.findBySearchText(searchText)
    }

}