package com.example.excursions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText
import com.example.excursions.ui.navigation.ExcursionsRoutes

@Composable
fun SavedDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                backDestination = { navController.navigateUp() },
                rightButtonDestination = null,
                rightButtonLabel = null
            )
        },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).height(300.dp)
        ) {
            ScreenTitleText(title = "Destination Name")
            Image(
                painter = painterResource(id = R.drawable.location_placeholder),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            ExcursionsButton(label = "Naviagate", onClick = { /*TODO*/ }, modifier = Modifier)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SavedDetailScreenPreview() {
    SavedDetailScreen(navController = rememberNavController())
}