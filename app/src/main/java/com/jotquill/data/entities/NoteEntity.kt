package com.jotquill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val noteType: Int,
    val noteTitle: String,
    val noteDate: String,
    val noteText: String,
    val noteAudio: ByteArray

)