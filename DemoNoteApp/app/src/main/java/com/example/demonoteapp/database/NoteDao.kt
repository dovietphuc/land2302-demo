package com.example.demonoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table WHERE note_table.id = :id")
    fun findById(id: Int): Note

    @Query("SELECT * FROM note_table " +
            "WHERE note_table.title LIKE '%' || :searchText || '%' " +
            "OR note_table.content LIKE '%' || :searchText || '%' " +
            "ORDER BY note_table.created_time DESC")
    fun findBySearchText(searchText: String = ""): LiveData<List<Note>>
}