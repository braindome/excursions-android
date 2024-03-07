package com.example.excursions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.R
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText

@Composable
fun SavedDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                backDestination = ExcursionsRoutes.Saved.route,
                rightButtonDestination = null,
                rightButtonLabel = null
            )
        },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            ScreenTitleText(title = "Destination Name")
            Image(
                painter = painterResource(id = R.drawable.location_placeholder),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(0.8f)
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