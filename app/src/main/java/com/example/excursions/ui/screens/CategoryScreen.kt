package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.model.PlaceList
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.ui.components.GridCard
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.theme.ExcursionsTheme

@Composable
fun CategoryScreen(
    navController: NavHostController,
    searchProfilesList: List<SearchProfile>,
    placeList: PlaceList,
    currentLocation: Center?,
    onScreenLaunch: () -> Unit,
    onSearchProfileReceived: (SearchProfile) -> Unit,
    onSearchPlaces: (center: Center?, types: List<String>, radius: Float, searchProfileId: Int) -> Unit,
) {

    DisposableEffect(Unit) {
        onScreenLaunch()
        onDispose {  }
    }
    
    var isEditModeOn by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val updatedSearchProfile = navBackStackEntry?.arguments?.getParcelable<SearchProfile>("updatedSearchProfile")

    LaunchedEffect(updatedSearchProfile) {
        updatedSearchProfile?.let { profile ->
            onSearchProfileReceived(profile)

        }
    }

    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                rightButtonLabel = if (!isEditModeOn) "Edit" else "Cancel",
                rightButtonDestination = null,
                onEndButtonClick = { isEditModeOn = !isEditModeOn }

            )
        },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ScreenTitleSubtitle(title = "Excursions", subtitle = "Categories", modifier = Modifier)
            Spacer(modifier = Modifier.padding(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(searchProfilesList) { searchProfile ->
                        //Timber.d("Search profile from CategoryScreen to GridCard: $searchProfile")
                        GridCard(
                            navController = navController,
                            searchProfile = searchProfile,
                            //viewModel = viewModel,
                            currentLocation = currentLocation,
                            placeList = placeList,
                            onSearchPlaces = onSearchPlaces as (Center?, List<String>, Float, Int) -> Unit,
                            isEditModeOn = isEditModeOn
                        )
                    }
                },
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            //Text(text = currentLocation.toString())
        }
    }


}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    ExcursionsTheme {
        // For the preview, you can pass null for NavController and create a dummy ExcursionsViewModel
        CategoryScreen(
            navController = rememberNavController(),
            searchProfilesList = TODO(),
            onScreenLaunch = TODO(),
            onSearchProfileReceived = TODO(),
            placeList = TODO(),
            currentLocation = TODO(),
            onSearchPlaces = TODO()
        )
    }
}