package com.example.mynotes


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.dbUtill.Note
import com.example.mynotes.dbUtill.NoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesAdapter(var notes:List<Note>,private var noteViewModel: NoteViewModel,private val repo: NoteRepo):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView:TextView = itemView.findViewById(R.id.contentTextView)
        val editBtn:ImageView = itemView.findViewById(R.id.updateButton)
        val deleteBtn:ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.editBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context,EditNotes::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteBtn.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                repo.delete(note)
                val data = repo.allNotes()
                withContext(Dispatchers.Main){
                    noteViewModel.setData(data)
                }
            }
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }

}