package com.mohaberabi.notekmp.android.details.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.notekmp.android.navigation.NoteNavConst
import com.mohaberabi.notekmp.domain.note.datasource.NoteDataSource
import com.mohaberabi.notekmp.domain.note.model.NoteModel
import com.mohaberabi.notekmp.domain.time.DateTimeUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class DetailViewModel(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val CONTENT_KEY = "CONTENT_KEY"
        const val TITLE_KEY = "TITLE_KEY"
        const val COLOR_KEY = "COLOR_KEY"

    }

    private var existingNoteId: Long? = null
    private val noteTitleFlow = savedStateHandle.getStateFlow(TITLE_KEY, "")
    private val noteContentFlow = savedStateHandle.getStateFlow(CONTENT_KEY, "")
    private val colorHexFlow =
        savedStateHandle.getStateFlow(COLOR_KEY, NoteModel.generateRandomColor())


    private val _isNotSaved = MutableStateFlow(false)
    val isNoteSaved = _isNotSaved.asStateFlow()
    val state =
        combine(
            noteTitleFlow,
            noteContentFlow,
            colorHexFlow
        ) { title, content, color ->
            DetailState(
                title = title,
                content = content,
                color = Color(color)
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            DetailState()
        )

    init {
        savedStateHandle.get<Long>(NoteNavConst.NOTE_ID_KEY)?.let { id ->
            if (id == -1L) {
                return@let
            }
            existingNoteId = id
            viewModelScope.launch {
                val note = noteDataSource.getNoteById(id)
                savedStateHandle[CONTENT_KEY] = note!!.content
                savedStateHandle[TITLE_KEY] = note.title
                savedStateHandle[COLOR_KEY] = note.colorHex
            }
        }
    }

    fun saveNote() {
        viewModelScope.launch {

            noteDataSource.insertNote(
                NoteModel(
                    id = existingNoteId,
                    title = noteTitleFlow.value,
                    content = noteContentFlow.value,
                    colorHex = colorHexFlow.value,
                    created = DateTimeUtil.now()
                )
            )
            _isNotSaved.update { true }
        }
    }

    fun onTitleChanged(title: String) {
        savedStateHandle[TITLE_KEY] = title
    }

    fun onContentChanged(content: String) {
        savedStateHandle[CONTENT_KEY] = content
    }


}