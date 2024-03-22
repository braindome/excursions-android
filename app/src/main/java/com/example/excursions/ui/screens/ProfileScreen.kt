package com.example.excursions.ui.screens

import androidx.car.app.connection.CarConnection
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.components.ExcursionsDropDown
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.components.UserViewListItem
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.navigation.ExcursionsTopBar

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel
) {
    //val carConnectionType by CarConnection(LocalContext.current).type.observeAsState(initial = -1)

    Scaffold(
        topBar = { ExcursionsTopBar(
            navController = navController,
            rightButtonDestination = "",
            rightButtonLabel = "") },
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
            //ProjectionState(carConnectionType = carConnectionType)
            Spacer(modifier = Modifier.weight(1f))
            UserViewListItem(label = "Logout", details = "email@user.com" )
            UserViewListItem(label = "Privacy Policy", details = "" )
        }
    }
}

@Composable
fun ProjectionState(carConnectionType: Int, modifier: Modifier = Modifier) {
    val text = when (carConnectionType) {
        CarConnection.CONNECTION_TYPE_NOT_CONNECTED -> "Not projecting"
        CarConnection.CONNECTION_TYPE_NATIVE -> "Running on Android Automotive OS"
        CarConnection.CONNECTION_TYPE_PROJECTION -> "Projecting"
        else -> "Unknown connection type"
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController(), viewModel = ExcursionsViewModel(api = DummyExcursionsAPI(), appContext = LocalContext.current))
}