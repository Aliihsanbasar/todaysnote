package com.jotquill.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jotquill.data.dao.NoteDao
import com.jotquill.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}

