package com.example.mynotes.dbUtill

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM mynote")
    fun getAllNotes():List<Note>

    @Query("SELECT * FROM mynote WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): Note

    @Delete
    suspend fun removeNote(note: Note)
}