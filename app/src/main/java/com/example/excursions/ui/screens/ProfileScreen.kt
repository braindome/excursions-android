package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar

@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = { ExcursionsTopBar(navController = navController, backDestination = "categories", rightButtonDestination = "", rightButtonLabel = "") },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "User settings and prefs")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Print places")
            }
        }
    }
}