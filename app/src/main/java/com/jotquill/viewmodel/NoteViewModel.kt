package com.jotquill.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jotquill.data.entities.NoteEntity
import com.jotquill.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _allNotes = MutableLiveData<List<NoteEntity>>()
    val allNotes: LiveData<List<NoteEntity>> = _allNotes

    init {
        getAllNotes()
    }

    fun insert(note: NoteEntity) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            _allNotes.value = repository.getAllNotes()
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

}