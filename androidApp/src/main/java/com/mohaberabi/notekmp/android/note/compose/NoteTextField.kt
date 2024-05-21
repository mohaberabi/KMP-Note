package com.mohaberabi.notekmp.android.note.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChanged: (String) -> Unit,
    isSearching: Boolean,
    onCloseClick: () -> Unit,
    onSearchClick: () -> Unit,
    hint: String = "Search"
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = isSearching,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(end = 40.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                placeholder = {
                    Text(text = hint)
                },
                value = value,
                onValueChange = onChanged
            )


        }
        AnimatedVisibility(
            visible = !isSearching,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {

            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            }
        }
        AnimatedVisibility(
            visible = isSearching,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {

            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = ""
                )
            }
        }
    }

}