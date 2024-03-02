package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.repository.SearchProfileRepository
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar
import timber.log.Timber

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel
) {
    Scaffold(
        topBar = { ExcursionsTopBar(navController = navController, backDestination = "categories", rightButtonDestination = "", rightButtonLabel = "") },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "User settings and prefs")
            Button(
                onClick = {
                    viewModel.searchPlacesByLocationAndRadius(
                        center = Center(41.274, 16.4207),
                        radius = 50000.00,
                        includedTypes = SearchProfileRepository.culturalExploration)
                    Timber.d(SearchProfileRepository.formatListForUi(SearchProfileRepository.culturalExploration).toString())
                }
            ) {
                Text(text = "Print places")
            }
        }
    }
}