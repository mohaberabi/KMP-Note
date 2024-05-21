package com.mohaberabi.notekmp.android.note.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.notekmp.domain.note.datasource.NoteDataSource
import com.mohaberabi.notekmp.domain.note.model.NoteModel
import com.mohaberabi.notekmp.domain.note.usecase.SearchNotesUseCase
import com.mohaberabi.notekmp.domain.time.DateTimeUtil
import com.mohaberabi.notekmp.presentation.RedPinkHex
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class NoteViewModel(
    private val dataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    companion object {
        const val NOTES_KEY = "notes"
        const val SEARCH_TEXT_KEY = "searchText"
        const val IS_SEARCHING_KEY = "isSearching"

    }

    private val searchNotesUseCase = SearchNotesUseCase()


    private val notesFlow = savedStateHandle.getStateFlow(NOTES_KEY, emptyList<NoteModel>())

    private val searchTextFlow = savedStateHandle.getStateFlow(SEARCH_TEXT_KEY, "")
    private val isSearchActiveFlow = savedStateHandle.getStateFlow(IS_SEARCHING_KEY, false)


    val state = combine(
        notesFlow,
        searchTextFlow,
        isSearchActiveFlow
    ) { notes, text, isSearching ->
        NoteState(
            searchNotesUseCase.execute(notes, text),
            text,
            isSearching
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
        NoteState()
    )

    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle[NOTES_KEY] = dataSource.getAllNotes()
        }
    }


    fun onSearchChanged(text: String) {
        savedStateHandle[SEARCH_TEXT_KEY] = text
    }


    fun onToggleSearch() {
        savedStateHandle[IS_SEARCHING_KEY] = !isSearchActiveFlow.value
        if (!isSearchActiveFlow.value) {
            savedStateHandle[SEARCH_TEXT_KEY] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            dataSource.deleteNoteBuyId(id)
            loadNotes()
        }
    }
}