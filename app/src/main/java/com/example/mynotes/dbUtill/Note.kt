package com.example.mynotes.dbUtill

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mynote")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val content:String)
