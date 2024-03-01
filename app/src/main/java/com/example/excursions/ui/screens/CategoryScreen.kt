package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsApp
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.GridCard
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun CategoryScreen(navController: NavHostController) {

    val mockCats = listOf("Eeny", "Meeny", "Miny", "Moe!", "Chip", "Chop", "Qui", "Quo", "Qua")

    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                backDestination = ExcursionsRoutes.Login.route,
                rightButtonLabel = "Add",
                rightButtonDestination = ExcursionsRoutes.AddSearchProfile.route
            )},
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            //Text(text = "Excursions", fontFamily = polestarFontFamily, fontSize = 32.sp, modifier = Modifier.padding(start = 10.dp))
            //Text(text = "Categories", fontFamily = polestarFontFamily, fontSize = 32.sp, modifier = Modifier.padding(start = 10.dp))
            ScreenTitleSubtitle(title = "Excursions", subtitle = "Categories", modifier = Modifier)
            Spacer(modifier = Modifier.padding(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(mockCats.size) { index ->
                        GridCard(
                            title = mockCats[index],
                            navController
                        )
                    }
                },
                modifier = Modifier.padding(8.dp))

        }
    }

}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen(navController = rememberNavController())
}