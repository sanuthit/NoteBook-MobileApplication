package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.dbUtill.NoteDB
import com.example.mynotes.dbUtill.NoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var repo: NoteRepo
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        repo = NoteRepo(NoteDB.getDatabase(this))

        noteViewModel.data.observe(this){
            notesAdapter = NotesAdapter(it,noteViewModel,repo)
            binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.notesRecyclerView.adapter = notesAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repo.allNotes()
            runOnUiThread {
                noteViewModel.setData(data)
            }
        }

        binding.addBtn.setOnClickListener{
            val intent = Intent(this,AddNotes::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            val data = repo.allNotes()
            runOnUiThread {
                noteViewModel.setData(data)
            }
        }
    }
}