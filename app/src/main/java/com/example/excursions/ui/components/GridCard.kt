package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.model.PlaceList
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.theme.YellowPolestar
import com.example.excursions.ui.theme.polestarFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun GridCard(
    navController: NavHostController,
    searchProfile: SearchProfile,
    placeList: PlaceList,
    onSearchPlaces: (Center?, List<String>, Float, Int) -> Unit,
    currentLocation: Center?,
    isEditModeOn: Boolean
) {
    val searchProfileId = searchProfile.id
    val nullCheckedLocation: Center? = currentLocation ?: Center(0.0,0.0)
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .width(173.dp)
            .height(200.dp)
            .padding(4.dp),
        color = YellowPolestar,
        onClick = {
            if (!isEditModeOn) {
                val types = searchProfile.types
                    .filter { it.isChecked }
                    .map { it.jsonName }
                Timber.d("Types into api request: $types")

                coroutineScope.launch {
                    // API call disabled for testing
                    onSearchPlaces(nullCheckedLocation, types, searchProfile.range, searchProfileId)
                    delay(300)
                    val placeListId = placeList.id
                    navController.navigate("swipeScreen/${placeListId}/${searchProfileId}")
                }
            } else {
                navController.navigate("${ExcursionsRoutes.EditSearchProfile.route}/${searchProfileId}")

            }


        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = searchProfile.title,
                fontSize = 22.sp,
                fontFamily = polestarFontFamily,
                modifier = Modifier.padding(2.dp)
            )
            Icon(
                painter = painterResource(id = if (isEditModeOn) R.drawable.plus_large else R.drawable.arrow_top_right),
                contentDescription = null,
                modifier = Modifier.align(Alignment.End).padding(8.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GridCardPreview() {
    GridCard(
        navController = rememberNavController(),
        searchProfile = SearchProfile(id = -1),
        isEditModeOn = false,
        placeList = PlaceList(),
        onSearchPlaces = {} as (Center?, List<String>, Float, Int) -> Unit,
        currentLocation = null
    )
}

