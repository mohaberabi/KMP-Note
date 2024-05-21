package com.mohaberabi.notekmp.android.note.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohaberabi.notekmp.domain.note.model.NoteModel
import com.mohaberabi.notekmp.domain.time.DateTimeUtil

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    backGroundColor: Color,
    onNoteClick: (NoteModel) -> Unit,
    note: NoteModel,
    onDelete: (NoteModel) -> Unit,
) {

    val formattedDate = remember(note.created) {

        DateTimeUtil.formatNoteDate(note.created)
    }


    Column(

        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(backGroundColor)
            .clickable {
                onNoteClick(note)
            }
            .padding(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "delete",
                modifier = Modifier.clickable(MutableInteractionSource(), null) {
                    onDelete(note)
                },
            )


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = note.content,
            fontWeight = FontWeight.Light,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formattedDate,
            color = Color.DarkGray,
        )

    }
}