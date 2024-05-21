package com.mohaberabi.notekmp.android.note.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.notekmp.android.MyApplicationTheme


@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onChanged: (String) -> Unit,
) {


    TextField(

        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(0.dp, Color.Transparent),
        value = value,
        onValueChange = onChanged,
        placeholder = {
            Text(
                text = hint, color = Color.DarkGray,
            )
        }
    )

}


@Preview(showBackground = true)
@Composable
private fun PreviewThis() {

    MyApplicationTheme {

        TransparentTextField(
            value = "",
            hint = "Iam Loser"
        ) {

        }
    }
}