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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    searchProfileId: Int
) {

    //val searchProfile by remember { mutableStateOf(viewModel.getSearchProfileById(searchProfileId)) }
    val searchProfile by remember { mutableStateOf(viewModel.getSearchProfileById(searchProfileId)) }
    //val searchProfile by viewModel.searchProfile.collectAsState()
    Timber.d("Collected search profile from vm: $searchProfile")

    //val searchProfileUiState by rememberSaveable { mutableStateOf(searchProfile) }

    //val updatedSearchProfileUiState = rememberUpdatedState(searchProfileUiState)

    //val navBackStackEntry by navController.currentBackStackEntryAsState()
    //navBackStackEntry?.savedStateHandle?.set("updatedSearchProfile", searchProfile)

    //val searchProfile = viewModel.searchProfile.collectAsState()

    //Timber.d("Search profile id: $searchProfileId")
    //Timber.d("Received search profile from card: $searchProfile")

    //val searchProfileUiState by remember { mutableStateOf(searchProfile) }



    Scaffold(
        topBar = { ExcursionsTopBar(
            navController = navController,
            backDestination = ExcursionsRoutes.Categories.route,
            rightButtonDestination = ExcursionsRoutes.Categories.route,
            rightButtonLabel = "Save",
            onEndButtonClick = {
                val updatedRange = searchProfile.range
                val updatedName = searchProfile.category.name
                val updatedState = updatedRange.let {
                    Category(name = updatedName)
                        .let { it2 -> searchProfile.copy(range = it, category = it2) }
                }

                viewModel.updateSearchProfileUiState(updatedState)
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
                input = searchProfile.category.name,
                onInputChanged = { updatedText ->
                    searchProfile.let { currentState ->
                        val updatedState = currentState.copy(category = currentState.category.copy(name = updatedText))
                        viewModel.updateSearchProfileUiState(updatedState)
                        viewModel.updateSearchProfileName(searchProfileId, updatedText)
                    }
                },
                modifier = Modifier)
            Spacer(modifier = Modifier.size(10.dp))
            //ExcursionsDropDown("Type")
            ExcursionsSearchField(label = "Search", input = "input", modifier = Modifier)
            Spacer(modifier = Modifier.size(10.dp))
            ExcursionsSlider { updatedValue ->
                //searchProfileUiState?.range ?: 0f //= updatedValue
                //viewModel.searchProfile.value.range = searchProfileUiState?.range!!
                searchProfile.let { currentState ->
                    val updatedState = currentState.copy(range = updatedValue)
                    viewModel.updateSearchProfileUiState(updatedState)
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Filter chips")
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    //.height(96.dp)
                    .padding(8.dp)
            ) {
                val types = searchProfile.category.types
                Timber.d("Filter chips types: $types")
                items(types.size) { index ->
                        ExcursionsFilterChip(label = formatStringForUI(types[index]))
                    }
            }
            Button(
                onClick = {
                    val updatedRange = searchProfile.range
                    val updatedState = updatedRange.let { searchProfile.copy(range = it) }
                    viewModel.updateSearchProfileUiState(updatedState)
                    searchProfile.let {
                        viewModel.searchPlacesByLocationAndRadius(
                            center = Center(40.3525, 18.1709),
                            searchProfile = it
                        )
                    }
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
        ) else it.toString()
    } }

    return words.joinToString(" ")
}


@Preview(showBackground = true)
@Composable
fun AddSearchProfilePreview() {
    EditSearchProfileScreen(
        navController = rememberNavController(),
        searchProfileId = 1,
        viewModel = ExcursionsViewModel(
            api = DummyExcursionsAPI(),
        )
    )
}
