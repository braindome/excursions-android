package com.example.excursions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText
import androidx.compose.ui.unit.dp
import com.example.excursions.ui.navigation.ExcursionsRoutes


@Composable
fun SwipeDetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                rightButtonDestination = null,
                rightButtonLabel = null
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
            ScreenTitleText(title = "Destination Name")
            Image(
                painter = painterResource(id = R.drawable.location_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SwipeDetailScreenPreview() {
    SwipeDetailScreen(navController = rememberNavController())
}