package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.SwipeActionBar
import com.example.excursions.ui.components.SwipeCard
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SwipeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ExcursionsTopBar(navController = navController, backDestination = "categories") },
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Category Name",
                fontFamily = polestarFontFamily,
                fontSize = 32.sp,
                modifier = Modifier.padding(start = 10.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            SwipeCard()
            Spacer(modifier = Modifier.weight(1f))
            SwipeActionBar()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwipeScreenPreview() {
    SwipeScreen(navController = rememberNavController())
}
