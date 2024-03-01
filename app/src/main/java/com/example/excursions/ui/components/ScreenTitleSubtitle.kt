package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun ScreenTitleSubtitle(title: String, subtitle: String, modifier: Modifier) {
    Column(
        modifier = modifier.padding(start = 8.dp)
    ) {
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
        Text(
            text = subtitle,
            fontFamily = polestarFontFamily,
            style = TextStyle(
                fontSize = 30.sp,
                lineHeight = 32.sp,
                color = GrayPolestar
            ),
            modifier = Modifier.width(342.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTitleSubtitlePreview() {
    ScreenTitleSubtitle("Title", "Subtitle", modifier = Modifier)
}