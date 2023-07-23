package com.example.demonoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteRoomDatabase? = null

        fun getInstance(context: Context) : NoteRoomDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, NoteRoomDatabase::class.java, "note_database.db")
                    .build()
            }

            return INSTANCE!!
        }
    }

}