package com.jotquill.data.repository

import com.jotquill.data.dao.NoteDao
import com.jotquill.data.entities.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insert(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    suspend fun deleteNote(id: Int) {
        noteDao.deleteNoteById(id)
    }
}