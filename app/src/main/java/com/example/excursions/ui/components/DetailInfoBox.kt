package com.example.excursions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.toCenter
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.calculateRatingAverage
import com.example.excursions.data.model.formatTypesToTags
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.data.repository.SearchProfileRepository
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.PolestarTypography
import com.example.excursions.ui.theme.Typography
import com.example.excursions.ui.theme.polestarFontFamily
import timber.log.Timber

@Composable
fun DetailInfoBox(
    place: PlaceState,
    viewModel: ExcursionsViewModel
) {
    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    val placeCoordinates = place.location.toCenter()
    val distanceToLocation = viewModel.distanceBetweenCenters(nullCheckedLocation, placeCoordinates)

    val ratingsAverage = place.calculateRatingAverage()
    Timber.d("Ratings: $ratingsAverage")

    Surface(
        modifier = Modifier
            //.height(454.dp)
            .fillMaxHeight(0.8f)
            .width(342.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.location_placeholder),
                contentDescription = null,
                modifier = Modifier.size(width = 343.dp, height = 216.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${distanceToLocation.toInt()} km",
                style = PolestarTypography.labelSmall
            )
            Text(text = "Rating: ${place.reviews?.calculateRatingAverage()}/5", style = PolestarTypography.labelSmall)
            Text(text = "Top Review", style = PolestarTypography.labelSmall)
            Text(text = place.reviews?.get(0)?.text?.text ?: "No reviews available")
            Text(text = place.types.formatTypesToTags())
            /*
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

             */


        }
    }



}

@Preview(showBackground = true)
@Composable
fun DetailInfoBoxPreview() {
    DetailInfoBox(
        place = SearchProfileRepository.dummyPlaceA,
        viewModel = ExcursionsViewModel(LocalContext.current, api = DummyExcursionsAPI())
    )
}