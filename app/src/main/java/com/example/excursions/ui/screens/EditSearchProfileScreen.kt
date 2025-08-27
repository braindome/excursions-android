package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.model.LocationType
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.components.ExcursionsFilterChip
import com.example.excursions.ui.components.ExcursionsSearchField
import com.example.excursions.ui.components.ExcursionsSlider
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.theme.ExcursionsTheme
import com.example.excursions.ui.theme.Typography
import timber.log.Timber

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditSearchProfileScreen(
    navController: NavHostController,
    // viewModel: ExcursionsViewModel,
    searchProfileId: Int,
    //currentLocation: Center
    onGetSearchProfile: (Int) -> SearchProfile,
    currentLocation: Center?,
    onUpdateSearchProfileState: (SearchProfile) -> Unit,
    onUpdateSearchProfileSliderPosition: (SearchProfile, Float) -> Unit,
    onUpdateLocationTypes: (Int, Int, Boolean) -> Unit,
) {

    val searchProfile by rememberSaveable(searchProfileId) { mutableStateOf(onGetSearchProfile(searchProfileId)) }
    var sliderPosition by rememberSaveable { mutableFloatStateOf(searchProfile.range) }

    // val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    Timber.d("NullCheckedLocation: ${nullCheckedLocation}")

    //Timber.d("Collected search profile from vm: $searchProfile")
    //Timber.d("Initial sliderPosition value: $sliderPosition")
    //Timber.d("Initial searchProfile.range: ${searchProfile.range}")

    //for (type in searchProfile.types) { Timber.d("Initial searchProfile.types: ${type.formattedName}, ${type.isChecked}") }

    Scaffold(
        topBar = { ExcursionsTopBar(
            navController = navController,
            //backDestination = { navController.navigateUp() },
            rightButtonDestination = ExcursionsRoutes.Categories.route,
            rightButtonLabel = "Save",
            onEndButtonClick = {
                val updatedRange = searchProfile.range
                val updatedName = searchProfile.title
                val updatedTypes = searchProfile.types
                val updatedState = SearchProfile(
                    title = updatedName,
                    range = updatedRange,
                    types = updatedTypes

                )

                // viewModel.updateSearchProfileUiState(updatedState)
                onUpdateSearchProfileState(updatedState)
                // viewModel.updateSearchProfileSliderPosition(searchProfile, sliderPosition)
                onUpdateSearchProfileSliderPosition(searchProfile, sliderPosition)
            })
        },
        bottomBar = { ExcursionsBottomBar(navController = navController) }

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            //ScreenTitleText(title = "Edit Search Profile")
            ScreenTitleSubtitle(title = "Edit", subtitle = searchProfile.title, modifier = Modifier)
            Spacer(modifier = Modifier.size(20.dp))
            ExcursionsTextField(
                label = "Name",
                input = searchProfile.title,
                onInputChanged = { updatedText ->
                    searchProfile.let { currentState ->
                        val updatedState = currentState.copy(title = updatedText)
                        // viewModel.updateSearchProfileUiState(updatedState)
                        onUpdateSearchProfileState(updatedState)
                        // viewModel.updateSearchProfileName(searchProfileId, updatedText)
                        onUpdateSearchProfileSliderPosition(searchProfile, sliderPosition)
                    }
                },
                modifier = Modifier)
            Spacer(modifier = Modifier.size(10.dp))
            //ExcursionsDropDown("Type")
            ExcursionsSearchField(label = "Search", input = "input", modifier = Modifier)
            Spacer(modifier = Modifier.size(10.dp))

            ExcursionsSlider(value = sliderPosition) { updatedValue ->
                sliderPosition = updatedValue
                //Timber.d("Slider value: $sliderPosition")
            }

            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Types",
                style = Typography.labelSmall,
                modifier = Modifier
                    .width(342.dp)
                    .height(26.dp),
            )

            FlowRow(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (type in searchProfile.types) {
                    ExcursionsFilterChip(
                        label = type.formattedName,
                        isSelected = type.isChecked,
                        onChipClicked = {
                            Timber.d("Clicked on type: ${type.formattedName}, isChecked: ${!type.isChecked}")
                            onUpdateLocationTypes(searchProfileId, type.id, !type.isChecked)
                        }
                    )
                }
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun EditSearchProfileScreenPreview() { // Renamed for clarity
    // Define dummy data for the SearchProfile
    val dummyTypes = listOf(
        LocationType(id = 1, jsonName = "restaurant", formattedName = "Restaurants", isChecked = true),
        LocationType(id = 2, jsonName = "park", formattedName = "Parks", isChecked = false),
        LocationType(
            id = 3,
            jsonName = "museum",
            formattedName = "Museums",
            isChecked = true,
        ),
        LocationType(id = 4, jsonName = "cafe", formattedName = "Cafes", isChecked = false)
    )
    val dummySearchProfile = SearchProfile(
        id = 1,
        title = "Weekend Fun", // Keep 'title' if your model uses it this way
        range = 2500f, // e.g., 2.5 km
    )

    // Dummy current location
    val dummyCurrentLocation = Center(latitude = 34.0522, longitude = -118.2437) // Los Angeles

    ExcursionsTheme { // Apply your theme for consistent UI
        EditSearchProfileScreen(
            navController = rememberNavController(),
            searchProfileId = 1,
            onGetSearchProfile = { id ->
                Timber.d("Preview: onGetSearchProfile called with id: $id")
                dummySearchProfile // Return the dummy profile
            },
            currentLocation = dummyCurrentLocation,
            onUpdateSearchProfileState = { updatedProfile ->
                Timber.d("Preview: onUpdateSearchProfileState called with: $updatedProfile")
                // In a real scenario, this would update the ViewModel/repository.
                // For preview, we might update a local state if we want to see changes reflected,
                // but EditSearchProfileScreen now manages its own 'searchProfile' state via rememberSaveable.
            },
            onUpdateSearchProfileSliderPosition = { profile, newRange ->
                Timber.d("Preview: onUpdateSearchProfileSliderPosition called. Profile: ${profile.title}, Range: $newRange")
            },
            onUpdateLocationTypes = { sProfileId, typeId, isChecked ->
                Timber.d("Preview: onUpdateLocationTypes called. SearchProfileId: $sProfileId, TypeId: $typeId, IsChecked: $isChecked")
            }
        )
    }
}
