package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excursions.data.api_models.AuthorAttribution
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Place
import com.example.excursions.data.api_models.Review
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.calculateRatingAverage
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.PolestarTypography
import timber.log.Timber

@Composable
fun DetailInfoBox(place: PlaceState) {

    val ratingsAverage = place.calculateRatingAverage()
    Timber.d("Ratings: $ratingsAverage")

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Rating: ${ratingsAverage}/5", style = PolestarTypography.labelSmall)
        place.reviews?.let { reviews ->
            if (reviews.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(reviews) { review ->
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = review.authorAttribution.displayName, modifier = Modifier.weight(1f))
                            Text(text = review.relativePublishTimeDescription, modifier = Modifier.weight(1f))
                        }
                        Text(text = review.originalText.text, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                        Text(text = "${review.rating}/5", modifier = Modifier.padding(bottom = 8.dp))
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = GrayPolestar
                        )
                    }
                }
            } else {
                Text(text = "No reviews available")
            }
        }
        Text(text = "Primary type")
        Text(text = "Type 1, Type 2, Type 3")

    }

}

@Preview(showBackground = true)
@Composable
fun DetailInfoBoxPreview() {
    DetailInfoBox(place = PlaceState())
}