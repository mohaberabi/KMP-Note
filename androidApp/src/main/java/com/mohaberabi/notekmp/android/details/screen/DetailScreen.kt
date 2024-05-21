package com.mohaberabi.notekmp.android.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohaberabi.notekmp.android.details.viewmodel.DetailState
import com.mohaberabi.notekmp.android.details.viewmodel.DetailViewModel
import com.mohaberabi.notekmp.android.note.compose.TransparentTextField
import org.koin.androidx.compose.koinViewModel


@Composable
fun DetailScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = koinViewModel(),
    onGoBack: () -> Unit,
) {

    val state by viewModel.state.collectAsState()

    val hasNotSaved by viewModel.isNoteSaved.collectAsState()
    LaunchedEffect(key1 = hasNotSaved) {
        if (hasNotSaved) {
            onGoBack()
        }

    }

    DetailScreen(
        state = state,
        onTitleChanged = viewModel::onTitleChanged,
        onContentChanged = viewModel::onContentChanged,
        onSave = viewModel::saveNote
    )
}


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit,
    onSave: () -> Unit,

    ) {

    Scaffold(
        modifier = Modifier.background(state.color),
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Color.Black,
                onClick = {
                    onSave()
                },
            ) {

                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save note",
                    tint = Color.White,
                )
            }
        }
    ) {

            padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(state.color)
                .fillMaxSize()
        ) {

            TransparentTextField(
                value = state.title,
                hint = "Learn Kotlin Coroutine",
                onChanged = {
                    onTitleChanged(it)
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
            TransparentTextField(
                value = state.content,
                hint = "Explore launch , async , await...",
                onChanged = {

                    onContentChanged(it)
                }
            )
        }
    }
}