package com.mohaberabi.notekmp.domain.note.model

import com.mohaberabi.notekmp.presentation.BabyBlueHex
import com.mohaberabi.notekmp.presentation.LightGreenHex
import com.mohaberabi.notekmp.presentation.RedOrangeHex
import com.mohaberabi.notekmp.presentation.RedPinkHex
import com.mohaberabi.notekmp.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class NoteModel(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(
            RedOrangeHex,
            RedPinkHex,
            BabyBlueHex,
            VioletHex,
            LightGreenHex,
        )

        fun generateRandomColor() = colors.random()
    }
}
