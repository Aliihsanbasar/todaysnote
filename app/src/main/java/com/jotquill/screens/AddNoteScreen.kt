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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jotquill.R
import com.jotquill.components.replaceEmptyTitle
import com.jotquill.data.entities.NoteEntity
import com.jotquill.models.NoteTypes
import com.jotquill.ui.theme.HardBeige
import com.jotquill.ui.theme.LighterBeige
import com.jotquill.ui.theme.NoteTextColor
import com.jotquill.ui.theme.NoteTitleColor
import com.jotquill.viewmodel.NoteViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavHostController, noteType: String) {

    val noteViewModel = hiltViewModel<NoteViewModel>()

    var headerText = ""
    var contentText = ""
    val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
    val date: String = df.format(Calendar.getInstance().time)

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
                IconButton(onClick = {

                    val note = NoteEntity(
                        noteType = noteType,
                        noteTitle = headerText.replaceEmptyTitle(),
                        noteText = contentText,
                        noteDate = date,
                        noteAudio = null
                    )

                    noteViewModel.insert(note)
                    navController.previousBackStackEntry?.savedStateHandle?.set("isSaved", true)
                    navController.popBackStack()


                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = HardBeige
                    )
                }
            })
    }) {

        TextNoteType(paddingValues = it,
            onTitleChange = { title ->
                headerText = title
            }, onContentChange = { content ->
                contentText = content
            })

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TextNoteType(
    paddingValues: PaddingValues,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit
) {

    var headText by remember { mutableStateOf("") }
    var conText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding(), start = 20.dp, end = 20.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = headText,
            onValueChange = { header ->
                if (header.length <= 25) {
                    headText = header
                    onTitleChange(headText)
                }
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
            value = conText,
            onValueChange = { content ->
                conText = content
                onContentChange(conText)
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
