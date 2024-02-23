package com.jotquill.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jotquill.data.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(user: NoteEntity)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Int)
}