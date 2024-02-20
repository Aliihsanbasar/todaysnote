package com.jotquill.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteCard() {

    Box(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .fillMaxWidth()
        .padding(vertical = 15.dp, horizontal = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Summer Vacation")
    }
}


@Preview
@Composable
private fun PreNoteCard() {
    NoteCard()
}