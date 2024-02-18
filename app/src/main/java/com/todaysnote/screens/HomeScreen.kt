package com.todaysnote.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.todaysnote.ui.theme.PurplePrimary

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        OutlinedTextField(value = "",
            onValueChange = {},
            singleLine = true,
            placeholder = {
                Text(text = "safdasdf")
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = PurplePrimary)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = PurplePrimary,
                unfocusedTextColor = Color.Gray,
                focusedBorderColor = PurplePrimary,
                unfocusedBorderColor = PurplePrimary
            )
        )
    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = NavHostController(LocalContext.current))
}