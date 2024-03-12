package com.example.excursions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SavedDestinationListItem() {
    Column(
        modifier = Modifier
            .width(342.dp)
            .height(72.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .width(342.dp)
                .height(52.dp)
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Item Name",
                    style = TextStyle(
                        fontSize = 26.sp,
                        lineHeight = 26.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = polestarFontFamily,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier.height(26.dp)
                )
                Text(
                    text = "666 km",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = polestarFontFamily,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.45f)
                    ),
                    modifier = Modifier
                        .width(64.dp)
                        .height(16.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.arrow_right
                    ),
                    contentDescription = null,
                    tint = OrangePolestar
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color.Black.copy(alpha = 0.3f))
    }

}
@Composable
fun SavedDestinationListItem_() {
    Column(
        modifier = Modifier
            .height(253.dp)
            .width(342.dp),
    ) {


        Image(
            painter = painterResource(id = R.drawable.location_placeholder),
            contentDescription = null,
            modifier = Modifier
                .height(145.dp)
                .width(342.dp),
            contentScale = ContentScale.Crop
        )


        Text(text = "5 km")
        Text(text = "Miami Beach", fontFamily = polestarFontFamily, fontSize = 30.sp)
        Spacer(modifier = Modifier.size(10.dp))
        PlainTextArrowButton(label = "Read More", onClick = {}, modifier = Modifier)



    }
}

@Preview(showBackground = true)
@Composable
fun SavedDestinationListItemPreview() {
    SavedDestinationListItem()
}