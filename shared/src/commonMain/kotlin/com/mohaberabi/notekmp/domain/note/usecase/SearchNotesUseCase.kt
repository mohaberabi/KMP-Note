package com.mohaberabi.notekmp.domain.note.usecase

import com.mohaberabi.notekmp.domain.note.model.NoteModel
import com.mohaberabi.notekmp.domain.time.DateTimeUtil

class SearchNotesUseCase {


    fun execute(notes: List<NoteModel>, query: String): List<NoteModel> {
        return if (query.isEmpty()) {
            notes
        } else {
            notes.filter {
                it.title.trim()
                    .lowercase()
                    .contains(query.lowercase()) ||
                        it.content.trim()
                            .lowercase()
                            .contains(query.lowercase())
            }.sortedBy { DateTimeUtil.toEpochMillis(it.created) }
        }
    }
}