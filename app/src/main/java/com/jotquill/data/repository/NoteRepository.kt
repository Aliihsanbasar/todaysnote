package com.jotquill.data.repository

import com.jotquill.data.dao.NoteDao
import com.jotquill.data.entities.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {
    //val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insert(note: NoteEntity) {
        noteDao.insertNote(note)
    }
}