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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.model.PlaceState
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SavedDestinationListItem(
    isEditModeOn: Boolean,
    onDeleteClicked: () -> Unit,
    place: PlaceState,
    distance: Int
) {
    Column(
        modifier = Modifier
            .width(342.dp)
            .height(72.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = place.displayName.text,
                    style = TextStyle(
                        fontSize = 26.sp,
                        lineHeight = 26.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = polestarFontFamily,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.6f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$distance km",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = polestarFontFamily,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.45f)
                    )
                )
            }

            if (isEditModeOn) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.cancel
                        ),
                        contentDescription = null,
                        tint = OrangePolestar
                    )
                }
            } else {
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
    SavedDestinationListItem(true,{}, PlaceState(DisplayName("", "Item Name")), 666)
}