package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.ui.components.DummyExcursionsAPI
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.ui.components.GridCard
import com.example.excursions.ui.theme.ExcursionsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

@Composable
fun CategoryScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel
) {

    DisposableEffect(Unit) {
        viewModel.fetchUserLocation()
        onDispose {  }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    //val updatedSearchProfile = navBackStackEntry?.savedStateHandle?.get<SearchProfile>("updatedSearchProfile")
    val updatedSearchProfile = navBackStackEntry?.arguments?.getParcelable<SearchProfile>("updatedSearchProfile")
    if (updatedSearchProfile != null) {
        viewModel.updateSearchProfilesList(listOf(updatedSearchProfile))
        //navBackStackEntry?.savedStateHandle?.remove<SearchProfile>("updatedSearchProfile")
    }

    val searchProfilesList by viewModel.searchProfilesList.collectAsState()
    val currentLocation by viewModel.location.observeAsState()
    Timber.d("Current coordinates: ${currentLocation?.latitude}, ${currentLocation?.longitude}")

    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                backDestination = ExcursionsRoutes.Login.route,
                rightButtonLabel = "Add",
                rightButtonDestination = ExcursionsRoutes.EditSearchProfile.route
            )},
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
                            viewModel = viewModel
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
        CategoryScreen(navController = rememberNavController(), viewModel = ExcursionsViewModel(api = DummyExcursionsAPI(), appContext = LocalContext.current))
    }
}