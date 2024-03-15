package com.example.excursions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.toCenter
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.components.DetailInfoBox
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.theme.polestarFontFamily
import timber.log.Timber

@Composable
fun PlaceDetailScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    placeId: String
) {
    Timber.d("placeId recevied via backstack: $placeId")
    val place = viewModel.getPlaceById(placeId)
    Timber.d("place via getPlaceById: $place")

    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    val placeCoordinates = place.location.toCenter()
    val distanceToLocation = viewModel.distanceBetweenCenters(nullCheckedLocation, placeCoordinates)
    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                backDestination = { navController.navigateUp() },
                rightButtonDestination = null,
                rightButtonLabel = null
            )
        },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = place.displayName.text,
                fontFamily = polestarFontFamily,
                style = TextStyle(color = Color.Black),
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(343.dp)
                    .padding(start = 24.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            //DetailInfoBox(place = place, viewModel = viewModel)
            DetailInfoBox(place = place, viewModel = viewModel)
            Spacer(modifier = Modifier.weight(1f))

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
            ExcursionsButton(
                label = "Navigate",
                onClick = { navController.navigate("${ExcursionsRoutes.Map.route}/${placeId}") },
                modifier = Modifier
            )




        }



    }
}

@Preview(showBackground = true)
@Composable
fun SavedDetailScreenPreview() {
    PlaceDetailScreen(
        navController = rememberNavController(),
        viewModel = ExcursionsViewModel(
            appContext = LocalContext.current,
            api = DummyExcursionsAPI()
        ),
        placeId = ""
    )
}