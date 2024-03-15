package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.components.SavedDestinationListItem
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily
import timber.log.Timber

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    searchProfileId: Int
) {

    val searchProfileList by viewModel.searchProfilesList.collectAsState()
    val filteredSearchProfile = searchProfileList.firstOrNull { it.id == searchProfileId }

    if (filteredSearchProfile == null) {
        // Show a placeholder layout or message (optional)
        Text(text = "Search Profile Not Found")
        return // Exit the composable if no profile found
    }

    Timber.d("Navarg searchProfileId: $searchProfileId")
    Timber.d("Search profile id from view model: ${filteredSearchProfile.id}")

    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)


    Timber.d("Favorite list: ${filteredSearchProfile.savedDestinations}")
    Timber.d("Favorite places size: ${filteredSearchProfile.savedDestinations.size}")

    val favoritePlaces = remember {
        mutableStateListOf<PlaceState>().apply {
            addAll(filteredSearchProfile.savedDestinations)
        }
    }



    var isEditModeOn by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                backDestination = { navController.navigateUp() },
                rightButtonLabel = if (!isEditModeOn) "Edit" else "Save",
                rightButtonDestination = null,
                onEndButtonClick = { isEditModeOn = !isEditModeOn }
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
                text = "Favorites",
                fontFamily = polestarFontFamily,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(343.dp)
                    .padding(start = 24.dp)
                    //.weight(0.5f),
            )
            Text(
                text = "Saved Destinations",
                fontFamily = polestarFontFamily,
                style = TextStyle(color = GrayPolestar),
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(343.dp)
                    .padding(start = 24.dp)
                    //.weight(0.5f),
            )
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val dummyList = listOf(
                    PlaceState(DisplayName("", "Item Name"), location = Location(12.34, 12.34)),
                    PlaceState(DisplayName("", "Item Name"), location = Location(12.34, 12.34))
                )
                //val filteredFavorites = searchProfile.savedDestinations.filter { it.isFavorite } // Filter list based on isFavorite
                items(
                    //dummyList
                    //filteredFavorites
                    favoritePlaces.filter { it.isFavorite }
                    //displayedPlaces, key = { it.id }
                ) { place ->
                    SavedDestinationListItem(
                        isEditModeOn = isEditModeOn,
                        onDeleteClicked = { removeFromFavoritePlaces(place, favoritePlaces) },
                        distance = viewModel.distanceBetweenCenters(
                            center1 = nullCheckedLocation,
                            center2 = Center(place.location.latitude, place.location.longitude)
                        ).toInt(),
                        place = place,
                        viewModel = viewModel,
                        searchProfile = filteredSearchProfile,
                    )
                }
            }

        }
    }


}

fun removeFromFavoritePlaces(place: PlaceState, list: SnapshotStateList<PlaceState>) {
    list.remove(place)
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(
        navController = rememberNavController(),
        viewModel = ExcursionsViewModel(LocalContext.current, DummyExcursionsAPI()),
        searchProfileId = -1
    )
}