package com.example.room.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)
    @Query("SELECT * FROM note_table")
    fun getNotes(): List<Note>
    @Query("DELETE FROM note_table")
    suspend fun deleteAllUsers()
}