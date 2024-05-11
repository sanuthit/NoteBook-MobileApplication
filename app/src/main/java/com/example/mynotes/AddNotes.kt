package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.mynotes.databinding.ActivityAddNotesBinding
import com.example.mynotes.dbUtill.Note
import com.example.mynotes.dbUtill.NoteDB
import com.example.mynotes.dbUtill.NoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNotes : AppCompatActivity() {
    private lateinit var binding: ActivityAddNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val repo = NoteRepo(NoteDB.getDatabase(this))

        binding.saveButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title,content)
            CoroutineScope(Dispatchers.IO).launch {
                repo.insert(note)
            }
            finish()
            Toast.makeText(this, "Note Save", Toast.LENGTH_SHORT).show()
        }

    }
}