package com.example.excursions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SavedDestinationListItem() {
    Column(
        modifier = Modifier
            .height(253.dp)
            .width(342.dp),
    ) {


        Image(
            painter = painterResource(id = R.drawable.location_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(1f)
                .size(width = 343.dp, height = 145.dp)
                .clip(RectangleShape)
        )


        Text(text = "5 km")
        Text(text = "Miami Beach", fontFamily = polestarFontFamily, fontSize = 30.sp)
        Spacer(modifier = Modifier.size(10.dp))
        PlainTextArrowButton(label = "Read More") {
            
        }



    }
}

@Preview(showBackground = true)
@Composable
fun SavedDestinationListItemPreview() {
    SavedDestinationListItem()
}