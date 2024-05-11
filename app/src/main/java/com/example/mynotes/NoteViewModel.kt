package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.dbUtill.Note

class NoteViewModel: ViewModel() {
    private val _data = MutableLiveData<List<Note>>()
    val data: LiveData<List<Note>> = _data
    
    fun setData(newdata:List<Note>){
        _data.value = newdata
    }
}