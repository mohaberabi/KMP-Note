package com.mohaberabi.notekmp.android.note.viewmodel

import com.mohaberabi.notekmp.domain.note.model.NoteModel

data class NoteState(

    val notes: List<NoteModel> = emptyList<NoteModel>(),
    val searchText: String = "",
    val isSearching: Boolean = false,
)
