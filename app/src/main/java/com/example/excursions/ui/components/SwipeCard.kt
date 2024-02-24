package com.example.excursions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SwipeCard() {
    Surface(
        modifier = Modifier
            .height(404.dp)
            .width(342.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.location_placeholder),
                contentDescription = null,
                modifier = Modifier.size(width = 343.dp, height = 216.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "5 km",
                fontFamily = polestarFontFamily,
                fontSize = 16.sp
            )
            Text(
                text = "Lake Michigan",
                fontFamily = polestarFontFamily,
                fontSize = 30.sp
            )
            Text(text = stringResource(id = R.string.lorem_ipsum))
            Spacer(modifier = Modifier.size(10.dp))
            PlainTextArrowButton(label = "Read more") {
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwipeCardPreview() {
    SwipeCard()
}