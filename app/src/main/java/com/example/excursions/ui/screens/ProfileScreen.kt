package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.repository.SearchProfileRepository
import com.example.excursions.ui.components.DummyExcursionsAPI
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.components.UserViewListItem
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ScreenTitleSubtitle(title = "Profile", subtitle = "email@user.com", modifier = Modifier)
            Spacer(modifier = Modifier.weight(1f))
            UserViewListItem(label = "Logout", details = "email@user.com" )
            UserViewListItem(label = "Privacy Policy", details = "" )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController(), viewModel = ExcursionsViewModel(api = DummyExcursionsAPI()))
}