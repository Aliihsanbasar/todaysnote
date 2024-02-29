package com.jotquill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val noteType: String = "",
    val noteTitle: String = "",
    val noteDate: String = "",
    val noteText: String? = null,
    val noteAudio: ByteArray? = null

)