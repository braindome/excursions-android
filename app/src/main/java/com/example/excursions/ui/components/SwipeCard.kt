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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.data.api_models.Place
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SwipeCard(
    place: Place
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
                text = "5 km",
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

/*
@Preview(showBackground = true)
@Composable
fun SwipeCardPreview() {
    SwipeCard(
        place =
            Place(
                displayName = DisplayName("", "Test name"),
                formattedAddress = "Address",
                primaryType = "Primary type",
                id = "1",
                location = Location(0.00, 0.00),
                types = listOf("marina", "hiking_area"),
                photos = listOf(
                    Photo(
                        authorAttributions = listOf(
                            AuthorAttribution(
                                displayName = "Author name",
                                photoUri = "//lh3.googleusercontent.com/a-/ALV-UjWnBRgwussq-jhV9VlXGhpvShPjaTuyctzXrc9jf2Opog=s100-p-k-no-mo",
                                uri = "//maps.google.com/maps/contrib/105369839496978573490"
                            ),
                    ),
                    heightPx = 200,
                    widthPx = 200,
                    name = "Photo name?"
                )
            )
        )
    )
}

 */