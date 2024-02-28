package com.jotquill.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.jotquill.models.NoteTypes

@Composable
fun AddNoteScreen(navController: NavHostController, noteType: String?) {
    Scaffold {
        it.calculateTopPadding()

        when (noteType) {
            NoteTypes.TEXT.name -> {
                Text(text = "TEXT")
            }

            NoteTypes.AUDIO.name -> {
                Text(text = "AUDIO")
            }
        }
    }
}