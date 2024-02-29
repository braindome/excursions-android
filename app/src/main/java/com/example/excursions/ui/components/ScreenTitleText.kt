package com.example.excursions.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun ScreenTitleText(title: String) {
    Text(
        text = title,
        fontFamily = polestarFontFamily,
        style = TextStyle(
            fontSize = 30.sp,
            lineHeight = 32.sp,
            color = Color.Black
        ),
        modifier = Modifier.width(342.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenTitleTextPreview() {
    ScreenTitleText(title = "Title")
}