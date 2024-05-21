package com.mohaberabi.notekmp.android.note.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.mohaberabi.notekmp.android.note.compose.NoteItem
import com.mohaberabi.notekmp.android.note.compose.NoteTextField
import com.mohaberabi.notekmp.android.note.viewmodel.NoteState
import com.mohaberabi.notekmp.android.note.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = koinViewModel(),
    onGoToDetails: (Long) -> Unit,
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {

        viewModel.loadNotes()
    }


    NoteListScreen(
        state = state,
        onSearchTextChange = viewModel::onSearchChanged,
        onCloseClick = viewModel::onToggleSearch,
        onNoteDelete = viewModel::deleteNoteById,
        onNoteClick = onGoToDetails,
        onSearchClick = viewModel::onToggleSearch,
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    state: NoteState,
    onSearchTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
    onNoteDelete: (Long) -> Unit,

    ) {


    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                onNoteClick(-1L)
            },
            shape = CircleShape,
            containerColor = Color.Black
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "", tint = Color.White
            )
        }
    }) {


        Column {
            Box(
                modifier = Modifier.padding(it),
                contentAlignment = Alignment.Center,
            ) {


                NoteTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    value = state.searchText,
                    onChanged = onSearchTextChange,
                    isSearching = state.isSearching,
                    onCloseClick = onCloseClick,
                    onSearchClick = onSearchClick
                )

                this@Column.AnimatedVisibility(
                    visible = !state.isSearching,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {

                    Text(
                        text = "All Notes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }


            }


            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = state.notes,
                ) { note ->
                    NoteItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        backGroundColor = Color(note.colorHex),
                        onNoteClick = {
                            onNoteClick(it.id!!)
                        },
                        onDelete = {
                            onNoteDelete(it.id!!)
                        },
                        note = note
                    )
                }
            }
        }

    }
}