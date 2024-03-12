package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
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
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.ui.components.DummyExcursionsAPI
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsFilterChip
import com.example.excursions.ui.components.ExcursionsSearchField
import com.example.excursions.ui.components.ExcursionsSlider
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText
import com.example.excursions.ui.theme.Typography
import timber.log.Timber

@Composable
fun EditSearchProfileScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    searchProfileId: Int,
    //currentLocation: Center
) {

    val searchProfile by rememberSaveable(searchProfileId) { mutableStateOf(viewModel.getSearchProfileById(searchProfileId)) }
    var sliderPosition by rememberSaveable { mutableFloatStateOf(searchProfile.range) }

    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)
    Timber.d("NullCheckedLocation: ${nullCheckedLocation}")

    //Timber.d("Collected search profile from vm: $searchProfile")
    //Timber.d("Initial sliderPosition value: $sliderPosition")
    //Timber.d("Initial searchProfile.range: ${searchProfile.range}")

    //for (type in searchProfile.types) { Timber.d("Initial searchProfile.types: ${type.formattedName}, ${type.isChecked}") }

    Scaffold(
        topBar = { ExcursionsTopBar(
            navController = navController,
            backDestination = ExcursionsRoutes.Categories.route,
            rightButtonDestination = ExcursionsRoutes.Categories.route,
            rightButtonLabel = "Save",
            onEndButtonClick = {
                val updatedRange = searchProfile.range
                val updatedName = searchProfile.name
                val updatedTypes = searchProfile.types
                val updatedState = SearchProfile(
                    name = updatedName,
                    range = updatedRange,
                    types = updatedTypes

                )

                viewModel.updateSearchProfileUiState(updatedState)
                viewModel.updateSearchProfileSliderPosition(searchProfile, sliderPosition)
            })},
        bottomBar = { ExcursionsBottomBar(navController = navController) }

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            ScreenTitleText(title = "Edit Search Profile")
            Spacer(modifier = Modifier.size(20.dp))
            ExcursionsTextField(
                label = "Name",
                input = searchProfile.name,
                onInputChanged = { updatedText ->
                    searchProfile.let { currentState ->
                        val updatedState = currentState.copy(name = updatedText)
                        viewModel.updateSearchProfileUiState(updatedState)
                        viewModel.updateSearchProfileName(searchProfileId, updatedText)
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

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                items(searchProfile.types) { locationType ->
                    ExcursionsFilterChip(
                        label = locationType.formattedName,
                        isSelected = locationType.isChecked,
                        onChipClicked = {
                            //val updatedTypes = searchProfile.types.toMutableList()
                            //val index = updatedTypes.indexOf(locationType)
                            //updatedTypes[index] = locationType.copy(isChecked = !locationType.isChecked)
                            //val updatedProfile = searchProfile.copy(types = updatedTypes)
                            //viewModel.updateSearchProfileUiState(updatedProfile)
                            //Timber.d("Updated types: $updatedTypes")
                            Timber.d("Clicked on type: ${locationType.formattedName}, isChecked: ${!locationType.isChecked}")
                            viewModel.updateLocationTypes(searchProfileId, locationType.id, !locationType.isChecked)
                        }
                    )
                }
            }

            Button(
                onClick = {
                    //Timber.d("Range value for api request: ${sliderPosition * 1000}")
                    val types = searchProfile.types
                        .filter { it.isChecked }
                        .map { it.jsonName }
                    Timber.d("Types into api request: $types")
                    viewModel.searchPlacesByLocationAndRadius(
                        center = nullCheckedLocation,
                        types = types,
                        range = sliderPosition
                    )


                },
                ) {
                Text(text = "Log API call")
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun AddSearchProfilePreview() {
    EditSearchProfileScreen(
        navController = rememberNavController(),
        searchProfileId = 1,
        viewModel = ExcursionsViewModel(
            api = DummyExcursionsAPI(),
            appContext = LocalContext.current
        )
    )
}
