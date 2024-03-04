package com.jotquill.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jotquill.R
import com.jotquill.models.NoteTypes
import com.jotquill.ui.theme.AudioIconColor
import com.jotquill.ui.theme.HardBeige
import com.jotquill.ui.theme.NoteDateColor
import com.jotquill.ui.theme.NoteTextColor
import com.jotquill.ui.theme.NoteTitleColor
import com.jotquill.ui.theme.ReminderIconColor
import com.jotquill.ui.theme.TextIconColor

@Composable
fun NoteCard(
    noteType: NoteTypes,
    noteTitle: String,
    noteDate: String,
    noteContent: String?,
    audioContent: ByteArray?,
    onDeleteClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 13.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            NoteHeader(noteType, noteTitle, noteDate, onDeleteClick)

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            )

            NoteContent(noteType, noteContent, audioContent)

        }

    }
}

@Composable
private fun NoteContent(noteType: NoteTypes, noteContent: String?, audioContent: ByteArray?) {

    when (noteType) {
        NoteTypes.TEXT -> {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                text = noteContent.toString(),
                fontFamily = FontFamily(Font(R.font.metropolisregular)),
                fontSize = 15.sp,
                color = NoteTextColor,
                lineHeight = 23.sp,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }

        NoteTypes.REMINDER -> {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                text = noteContent.toString(),
                fontFamily = FontFamily(Font(R.font.metropolisregular)),
                fontSize = 15.sp,
                color = NoteTextColor,
                lineHeight = 23.sp,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }

        NoteTypes.AUDIO -> {
            AudioNote(audioContent)
        }

        else -> {
            null
        }
    }


}

@Composable
fun AudioNote(audioContent: ByteArray?) {

    var isPlaying by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
    ) {

        IconButton(modifier = Modifier.background(HardBeige, shape = CircleShape),
            onClick = { isPlaying = !isPlaying }) {
            Icon(imageVector = if(isPlaying)Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null)
        }

        VoiceBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .height(48.dp),
            barWidth = 3.dp,
            gapWidth = 2.dp,
            isAnimating = isPlaying,
        )
    }
}

@Composable
private fun NoteHeader(
    noteType: NoteTypes,
    noteTitle: String,
    noteDate: String,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(
                        when (noteType) {
                            NoteTypes.TEXT -> {
                                TextIconColor
                            }

                            NoteTypes.REMINDER -> {
                                ReminderIconColor
                            }

                            NoteTypes.AUDIO -> {
                                AudioIconColor
                            }

                            else -> {
                                Color.LightGray
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = when (noteType) {
                        NoteTypes.TEXT -> {
                            painterResource(id = R.drawable.ic_text)
                        }

                        NoteTypes.REMINDER -> {
                            painterResource(id = R.drawable.ic_reminder)
                        }

                        NoteTypes.AUDIO -> {
                            painterResource(id = R.drawable.ic_audio)
                        }

                        else -> {
                            painterResource(id = R.drawable.ic_text)
                        }
                    }, contentDescription = null
                )
            }
            Column(
                modifier = Modifier.padding(start = 14.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = noteTitle,
                    fontFamily = FontFamily(Font(R.font.metropolissemibold)),
                    fontSize = 16.sp,
                    color = NoteTitleColor
                )
                Text(
                    text = noteDate,
                    fontFamily = FontFamily(Font(R.font.metropolismedium)),
                    fontSize = 12.sp,
                    color = NoteDateColor
                )
            }
        }


        IconButton(
            onClick = {
                onDeleteClick.invoke()
            }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = Color.Red.copy(alpha = 0.5f)
            )
        }
    }
}


@Preview
@Composable
private fun PreNoteCard() {
    NoteCard(NoteTypes.AUDIO, "Audio Note", "27 Jun 2024, 12:00 PM", null, byteArrayOf(), {})
}

@Preview
@Composable
private fun PreAudio() {
    AudioNote(audioContent = byteArrayOf())
}