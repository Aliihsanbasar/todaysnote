package com.jotquill.components

import com.jotquill.models.NoteTypes

fun returnNoteType(noteType: String): NoteTypes {
    when(noteType){
        NoteTypes.TEXT.name -> {
            return NoteTypes.TEXT
        }
        NoteTypes.AUDIO.name -> {
            return NoteTypes.AUDIO
        }
        else ->{
            return NoteTypes.TEXT
        }
    }
}