package com.example.mynotes.dbUtill

class NoteRepo (private val db: NoteDB){
    suspend fun insert(note: Note) = db.noteDao().addNote(note)
    suspend fun update(note: Note) = db.noteDao().updateNote(note)
    suspend fun delete(note: Note) = db.noteDao().removeNote(note)
    suspend fun getNote(noteId:Int):Note = db.noteDao().getNoteById(noteId)

    fun allNotes():List<Note> = db.noteDao().getAllNotes()

}