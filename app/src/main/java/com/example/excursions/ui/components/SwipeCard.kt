package com.example.excursions.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
import com.example.excursions.data.api_models.Place
import com.example.excursions.data.api_models.toCenter
import com.example.excursions.data.model.PlaceState
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SwipeCard(
    place: PlaceState,
    viewModel: ExcursionsViewModel
) {

    /*
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = place.photos[0].authorAttributions[0].photoUri).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.location_placeholder)
                scale(Scale.FILL)
            }).build()
    )

     */

    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    val placeCoordinates = place.location.toCenter()
    val distanceToLocation = viewModel.distanceBetweenCenters(nullCheckedLocation, placeCoordinates)

    Surface(
        modifier = Modifier
            .height(404.dp)
            .width(342.dp)
    ) {
        Column {
            /*
            AsyncImage(
                model = place.photos[0].authorAttributions[0].uri,
                contentDescription = "Picture",
                modifier = Modifier.size(width = 343.dp, height = 216.dp),
                contentScale = ContentScale.Crop
            )

             */

            Image(
                painter = painterResource(id = R.drawable.location_placeholder),
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
            Text(text = stringResource(id = R.string.lorem_ipsum))
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Primary type: ${place.primaryType}",
                )

            Text(
                text = "Tags: ${place.types}",
                modifier = Modifier.height(100.dp)
            )

            PlainTextArrowButton(
                label = "Read more",
                onClick = {},
                modifier = Modifier)

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
        )
    )
}

