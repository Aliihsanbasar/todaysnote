package com.jotquill.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jotquill.data.entities.NoteEntity
import com.jotquill.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    //val allNotes = repository.allNotes

    fun insert(note: NoteEntity) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}