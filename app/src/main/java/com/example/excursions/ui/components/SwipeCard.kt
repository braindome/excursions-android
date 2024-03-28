package com.example.excursions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
import com.example.excursions.data.api_models.toCenter
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.calculateRatingAverage
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.theme.polestarFontFamily
import timber.log.Timber

@Composable
fun SwipeCard(
    navController: NavHostController,
    place: PlaceState,
    viewModel: ExcursionsViewModel,
) {
    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    val placeCoordinates = place.location.toCenter()
    val distanceToLocation = viewModel.distanceBetweenCenters(nullCheckedLocation, placeCoordinates)
    val placeId = place.id

    Surface(
        modifier = Modifier
            //.height(454.dp)
            .fillMaxHeight(0.8f)
            .width(342.dp)
    ) {
        Column {
            Image(
                //painter = rememberAsyncImagePainter("https:${place.photos?.get(0)?.authorAttributions?.get(0)?.photoUri}"),
                painter = rememberAsyncImagePainter(model = "https://places.googleapis.com/v1/${place.photos?.get(0)?.name}/media?maxHeightPx=400&maxWidthPx=400&key=${BuildConfig.API_KEY}"),
                contentDescription = null,
                modifier = Modifier.size(width = 343.dp, height = 216.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "${distanceToLocation.toInt()} km",
                fontFamily = polestarFontFamily,
                fontSize = 16.sp
            )
            Text(
                text = place.displayName.text,
                fontFamily = polestarFontFamily,
                fontSize = 30.sp
            )
            //MainInfoBox(place = place)
            Column {
                Text(text = "${place.calculateRatingAverage()}/5 Stars!")
                //Text(text = stringResource(id = R.string.lorem_ipsum))
                Spacer(modifier = Modifier.size(10.dp))
                //Text(text = "Primary type: ${place.primaryType}",)
                //Text(text = "Tags: ${place.types.formatTypesToTags()}",)
                PlainTextArrowButton(
                    label = "Read more",
                    onClick = { navController.navigate("${ExcursionsRoutes.PlaceDetailScreen.route}/${placeId}") },
                    modifier = Modifier
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SwipeCardPreview() {
    SwipeCard(
        place = PlaceState(
            displayName = DisplayName(languageCode = "EN", text = "Display Name"),
            formattedAddress = "Test formatted address",
            id = "hehehe",
            location = Location(0.00,0.00),
            primaryType = "Marina",
            types = listOf("Marina", "Hotel")
        ),
        viewModel = ExcursionsViewModel(
            appContext = LocalContext.current,
            api = DummyExcursionsAPI()
        ),
        navController = rememberNavController()

    )
}

