package com.mohaberabi.notekmp.android.details.viewmodel

import androidx.compose.ui.graphics.Color

data class DetailState(
    val title: String = "",
    val content: String = "",
    val color: Color = Color.White,
)
