package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.mynotes.databinding.ActivityEditNotesBinding
import com.example.mynotes.dbUtill.Note
import com.example.mynotes.dbUtill.NoteDB
import com.example.mynotes.dbUtill.NoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNotes : AppCompatActivity() {
    private lateinit var binding: ActivityEditNotesBinding
    private lateinit var note: Note
    private var noteId:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = NoteRepo(NoteDB.getDatabase(this))

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }
        CoroutineScope(Dispatchers.IO).launch{
            val note = repo.getNote(noteId)
            binding.editTitleEditText.setText(note.title)
            binding.editContentEditText.setText(note.content)
        }

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.editTitleEditText.text.toString()
            val newContent = binding.editContentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            CoroutineScope(Dispatchers.IO).launch {
                repo.update(updateNote)
            }
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }



    }
}