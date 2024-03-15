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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.components.SavedDestinationsFolderCard
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.navigation.ExcursionsRoutes
import timber.log.Timber

@Composable
fun SavedDestinationsScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel
) {
    DisposableEffect(Unit) {
        viewModel.fetchUserLocation()
        onDispose {  }
    }

    val searchProfilesList by viewModel.searchProfilesList.collectAsState()
    val currentLocation by viewModel.location.observeAsState()
    Timber.d("Current coordinates: ${currentLocation?.latitude}, ${currentLocation?.longitude}")

    val mockCats = listOf("Beaches", "Monuments", "Hiking", "Explorations", "Swimming Spots")

    Scaffold(
        topBar = { ExcursionsTopBar(navController = navController, backDestination = {navController.navigateUp()}, rightButtonLabel = "", rightButtonDestination = ExcursionsRoutes.EditSearchProfile.route) },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Text(text = "Excursions", fontFamily = polestarFontFamily, fontSize = 32.sp, modifier = Modifier.padding(start = 10.dp))
            //Text(text = "Categories", fontFamily = polestarFontFamily, fontSize = 32.sp, modifier = Modifier.padding(start = 10.dp))
            ScreenTitleSubtitle(title = "Excursions", subtitle = "Saved Destinations", modifier = Modifier)
            Spacer(modifier = Modifier.padding(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(searchProfilesList) { searchProfile ->
                        SavedDestinationsFolderCard(
                            navController = navController,
                            searchProfile = searchProfile,
                            viewModel = viewModel
                        )
                    }
                },
                modifier = Modifier.padding(8.dp))

        }
    }

}

@Preview(showBackground = true)
@Composable
fun SavedDestinationsScreenPreview() {
    SavedDestinationsScreen(navController = rememberNavController(), viewModel = ExcursionsViewModel(api = DummyExcursionsAPI(), appContext = LocalContext.current))
}