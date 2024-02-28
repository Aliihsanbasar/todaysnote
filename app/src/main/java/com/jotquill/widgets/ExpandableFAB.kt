package com.jotquill.widgets

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jotquill.R
import com.jotquill.models.NoteTypes
import com.jotquill.ui.theme.AudioIconColor
import com.jotquill.ui.theme.HardBeige
import com.jotquill.ui.theme.TextIconColor


@Composable
fun CustomFloatingActionButton(
    expandable: Boolean,
    fabIcon: ImageVector,
    onNoteClick: (NoteTypes) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    if (!expandable) { // Close the expanded fab if you change to non expandable nav destination
        isExpanded = false
    }

    val fabSize = 64.dp
    val expandedFabWidth by animateDpAsState(
        targetValue = if (isExpanded) 175.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f)
    )
    val expandedFabHeight by animateDpAsState(
        targetValue = if (isExpanded) 58.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f)
    )

    Column {

        // ExpandedBox over the FAB
        Box(
            modifier = Modifier
                .offset(y = (25).dp)
                .size(
                    width = expandedFabWidth,
                    height = (animateDpAsState(
                        if (isExpanded) 175.dp else 0.dp,
                        animationSpec = spring(dampingRatio = 4f)
                    )).value
                )
                .background(
                    HardBeige.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Row(
                    modifier = Modifier.clickable {
                        onNoteClick(NoteTypes.TEXT)
                    },
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(40.dp)
                            .background(TextIconColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_text),
                            contentDescription = ""
                        )

                    }
                    Text(
                        text = "Yazılı Not",
                        fontFamily = FontFamily(Font(R.font.metropolismedium))
                    )
                }
                Row(
                    modifier = Modifier.clickable {
                        onNoteClick(NoteTypes.AUDIO)
                    },
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(40.dp)
                            .background(AudioIconColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_audio),
                            contentDescription = null
                        )

                    }
                    Text(text = "Sesli Not", fontFamily = FontFamily(Font(R.font.metropolismedium)))
                }
            }
        }

        FloatingActionButton(
            onClick = {
                //onFabClick()
                if (expandable) {
                    isExpanded = !isExpanded
                }
            },
            modifier = Modifier
                .width(expandedFabWidth)
                .height(expandedFabHeight),
            shape = RoundedCornerShape(18.dp),
            containerColor = HardBeige

        ) {

            Icon(
                imageVector = fabIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .offset(
                        x = animateDpAsState(
                            if (isExpanded) -70.dp else 0.dp,
                            animationSpec = spring(dampingRatio = 3f)
                        ).value
                    )
            )

            Text(
                text = "Capture Your Mind",
                softWrap = false,
                modifier = Modifier
                    .offset(
                        x = animateDpAsState(
                            if (isExpanded) 10.dp else 50.dp,
                            animationSpec = spring(dampingRatio = 3f)
                        ).value
                    )
                    .alpha(
                        animateFloatAsState(
                            targetValue = if (isExpanded) 1f else 0f,
                            animationSpec = tween(
                                durationMillis = if (isExpanded) 350 else 100,
                                delayMillis = if (isExpanded) 100 else 0,
                                easing = EaseIn
                            )
                        ).value
                    )
            )

        }
    }
}


@Preview
@Composable
fun CustomFloatingActionButtonPreview() {
    CustomFloatingActionButton(
        expandable = true,
        onNoteClick = {},
        fabIcon = Icons.Filled.Edit
    )

}