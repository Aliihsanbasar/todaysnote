package com.jotquill.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jotquill.ui.theme.HardBeige
import com.jotquill.ui.theme.LighterBeige
import com.jotquill.ui.theme.SoftBeige
import com.jotquill.widgets.JotQuillChips
import com.jotquill.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(), containerColor = LighterBeige,
        topBar = {
            TopAppBar(modifier = Modifier.padding(top = 36.dp),
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = LighterBeige),
                title = {
                    Text(text = "JotQuill",
                        fontFamily = FontFamily(Font(resId = R.font.metropolisbold)),
                        fontSize = 23.sp,
                        color = HardBeige
                    )
            })
        }
    ) {

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Column (modifier = Modifier.fillMaxSize()){
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = it.calculateTopPadding()),
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                    placeholder = {
                        Text(text = "Search Your Future", color = SoftBeige)
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = HardBeige)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HardBeige,
                        unfocusedTextColor = HardBeige,
                        focusedBorderColor = HardBeige,
                        unfocusedBorderColor = HardBeige
                    )
                )

                FilterChips()
            }
        }
    }
}


@Composable
fun FilterChips() {
    val scrollState = rememberScrollState()
    val textNote = remember { mutableStateOf(false) }
    val reminder = remember { mutableStateOf(false) }
    val audio = remember { mutableStateOf(false) }
    val image = remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .padding(top = 21.dp)
        .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(space = 15.dp)
    ) {
        JotQuillChips("Text Notes", textNote) {

        }
        JotQuillChips("Reminder", reminder) {

        }
        JotQuillChips("Audio", audio) {

        }
        JotQuillChips("Images", image) {

        }

    }
}



@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = NavHostController(LocalContext.current))
}