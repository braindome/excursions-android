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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import coil.compose.rememberAsyncImagePainter
import com.example.excursions.BuildConfig
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.toCenter
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.model.calculateRatingAverage
import com.example.excursions.data.model.formatTypesToTags
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.data.repository.SearchProfileRepository
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.components.ScreenTitleText
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.theme.PolestarTypography
import com.example.excursions.ui.theme.polestarFontFamily
import timber.log.Timber

@Composable
fun SavedDestinationDetailScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    placeId: String,
    searchProfileId: Int
) {

    LaunchedEffect(placeId, searchProfileId) {
        viewModel.getFavoriteFromFirestore(placeId, searchProfileId)
    }

    val place by viewModel.favoritePlace.collectAsState()
    //Timber.d("get place: $place")

    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    val placeCoordinates = place?.location?.toCenter()
    val distanceToLocation =
        placeCoordinates?.let { viewModel.distanceBetweenCenters(nullCheckedLocation, it) }
    val ratingsAverage = place?.calculateRatingAverage()

    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                rightButtonDestination = "",
                rightButtonLabel = ""
            )
        },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
                //.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            place?.displayName?.let { ScreenTitleText(title = it.text) }
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                painter = rememberAsyncImagePainter(model = "https://places.googleapis.com/v1/${place?.photos?.get(0)?.name}/media?maxHeightPx=400&maxWidthPx=400&key=${BuildConfig.PLACES_API_KEY}"),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(10.dp))
            if (distanceToLocation != null) {
                Text(
                    text = "${distanceToLocation.toInt()} km",
                    style = PolestarTypography.labelSmall
                )
            }
            Text(text = "Rating: ${place?.reviews?.calculateRatingAverage()}/5", style = PolestarTypography.labelSmall)
            Text(text = "Top Review", style = PolestarTypography.labelSmall)
            Text(text = place?.reviews?.get(0)?.text?.text ?: "No reviews available")
            place?.types?.let { Text(text = it.formatTypesToTags()) }
            Spacer(modifier = Modifier.weight(1f))
            ExcursionsButton(
                label = "Navigate",
                onClick = { navController.navigate("${ExcursionsRoutes.Map.route}/${placeId}") },
                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DestinationDetailScreenPreview() {
    SavedDestinationDetailScreen(
        navController = rememberNavController(),
        viewModel = ExcursionsViewModel(LocalContext.current, api = DummyExcursionsAPI()),
        placeId = "asd",
        searchProfileId = 1

    )
}