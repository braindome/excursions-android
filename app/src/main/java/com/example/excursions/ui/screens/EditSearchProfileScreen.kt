package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.repository.Category
import com.example.excursions.ui.components.DummyExcursionsAPI
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsFilterChip
import com.example.excursions.ui.components.ExcursionsSearchField
import com.example.excursions.ui.components.ExcursionsSlider
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText
import timber.log.Timber
import java.util.Locale

@Composable
fun EditSearchProfileScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    searchProfileId: Int,
    //currentLocation: Center
) {

    //val searchProfile by remember { mutableStateOf(viewModel.getSearchProfileById(searchProfileId)) }
    val searchProfile by remember { mutableStateOf(viewModel.getSearchProfileById(searchProfileId)) }
    var sliderPosition by rememberSaveable { mutableStateOf(searchProfile.range) }
    //val searchProfile by viewModel.searchProfile.collectAsState()
    Timber.d("Collected search profile from vm: $searchProfile")
    //val navBackStackEntry by navController.currentBackStackEntryAsState()
    //navBackStackEntry?.savedStateHandle?.set("updatedSearchProfile", searchProfile)

    //Timber.d("Search profile id: $searchProfileId")
    //Timber.d("Received search profile from card: $searchProfile")



    Scaffold(
        topBar = { ExcursionsTopBar(
            navController = navController,
            backDestination = ExcursionsRoutes.Categories.route,
            rightButtonDestination = ExcursionsRoutes.Categories.route,
            rightButtonLabel = "Save",
            onEndButtonClick = {
                val updatedRange = searchProfile.range
                val updatedName = searchProfile.name
                val updatedState = updatedRange.let {
                    Category(name = updatedName)
                        .let { it2 -> searchProfile.copy(range = it) }
                }

                viewModel.updateSearchProfileUiState(updatedState)
                viewModel.updateSearchProfileSliderPosition(searchProfileId, sliderPosition)
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
                        //val updatedState = currentState.copy(category = currentState.category.copy(name = updatedText))
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
                Timber.d("Slider value: $sliderPosition")
            }

            /*
            ExcursionsSlider { updatedValue ->
                //searchProfileUiState?.range ?: 0f //= updatedValue
                //viewModel.searchProfile.value.range = searchProfileUiState?.range!!
                searchProfile.let { currentState ->
                    val updatedState = currentState.copy(range = updatedValue)
                    viewModel.updateSearchProfileUiState(updatedState)
                }
            }

             */
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Filter chips")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    //.height(96.dp)
                    .padding(8.dp)
            ) {
                val types = searchProfile.types.map { it.jsonName }

                Timber.d("Filter chips types: $types")
                items(types.size) { index ->
                        ExcursionsFilterChip(label = formatStringForUI(types[index]))
                    }
            }
            Button(
                onClick = {
                    Timber.d("Current slider value: $sliderPosition")
                    /*
                    viewModel.searchPlacesByLocationAndRadius(
                        center = Center(40.3548, 18.1717),
                        //center = currentLocation,
                        searchProfile = searchProfile
                    )

                     */


                    viewModel.searchPlacesByLocationAndRadiusTest(
                        center = Center(40.3548, 18.1717),
                        types = searchProfile.types.map { it.jsonName },
                        range = sliderPosition * 1000
                    )




                },
                ) {
                Text(text = "Log API call")
            }
        }

    }
}

fun formatStringForUI(input: String): String {
    val words = input.split("_").map { it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        )else it.toString()
    } }

    return words.joinToString(" ")
}


@Preview(showBackground = true)
@Composable
fun AddSearchProfilePreview() {
    EditSearchProfileScreen(
        navController = rememberNavController(),
        //currentLocation = Center(0.0000, 0.0000),
        searchProfileId = 1,
        viewModel = ExcursionsViewModel(
            api = DummyExcursionsAPI(),
        )
    )
}
