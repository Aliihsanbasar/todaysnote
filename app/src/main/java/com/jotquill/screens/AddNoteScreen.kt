package com.jotquill.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jotquill.R
import com.jotquill.models.NoteTypes
import com.jotquill.ui.theme.HardBeige
import com.jotquill.ui.theme.LighterBeige
import com.jotquill.ui.theme.NoteTextColor
import com.jotquill.ui.theme.NoteTitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavHostController, noteType: String?) {


    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = LighterBeige, topBar = {
        TopAppBar(modifier = Modifier.padding(20.dp),
            title = {},
            colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = LighterBeige),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = HardBeige
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = HardBeige
                    )
                }
            })
    }) {

        TextNoteType(it)


//        when (noteType) {
//            NoteTypes.TEXT.name -> {
//                Text(text = "TEXT")
//            }
//
//            NoteTypes.AUDIO.name -> {
//                Text(text = "AUDIO")
//            }
//        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TextNoteType(
    it: PaddingValues,
) {
    var headerText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = it.calculateTopPadding(), start = 20.dp, end = 20.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = headerText,
            onValueChange = { header ->
                if (header.length <= 25) headerText = header
            },
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.metropolisbold)),
                fontSize = 23.sp,
                color = NoteTitleColor
            ),
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = HardBeige.copy(alpha = 0.5f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Add Title", color = NoteTitleColor) },
            shape = RoundedCornerShape(5.dp)
        )

        TextField(
            modifier = Modifier
                .padding(top = 35.dp)
                .fillMaxWidth(),
            value = contentText,
            onValueChange = { content ->
                contentText = content
            },
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.metropolisregular)),
                fontSize = 15.sp,
                color = NoteTextColor,
                lineHeight = 23.sp
            ),
            colors = TextFieldDefaults.textFieldColors().copy(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = HardBeige.copy(alpha = 0.5f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Add Note", color = NoteTextColor) },
            shape = RoundedCornerShape(5.dp)
        )
    }
}

@Preview
@Composable
fun AddNoteScreenPreview() {
    AddNoteScreen(
        navController = NavHostController(LocalContext.current), noteType = NoteTypes.TEXT.name
    )
}
