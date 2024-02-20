package com.jotquill.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jotquill.ui.theme.HardBeige
import com.jotquill.ui.theme.SoftBeige

@Composable
fun JotQuillChips(chipName: String, selected: MutableState<Boolean>, chipsClick: () -> Unit) {

    val selected1 = remember { mutableStateOf(selected) }


    FilterChip(onClick = {
            selected1.value.value = !selected1.value.value
            chipsClick.invoke()
        },
        label = {
            Text(chipName)
        },
        selected = selected1.value.value,
        leadingIcon = if (selected1.value.value) {
        {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "Done icon",
                modifier = Modifier.size(FilterChipDefaults.IconSize)
            )
        }
        } else {
            null
        },
        border = BorderStroke(width = 2.dp, color = HardBeige),
        colors = FilterChipDefaults.filterChipColors().copy(containerColor = SoftBeige, selectedContainerColor = HardBeige)
    )
}

@Preview
@Composable
private fun PreChips(){
    JotQuillChips("Reminder", remember { mutableStateOf(false) }, {})
}