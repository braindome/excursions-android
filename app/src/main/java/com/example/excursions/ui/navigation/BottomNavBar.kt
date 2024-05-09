package com.example.excursions.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.OrangePolestar

/**
 * This file contains the Bottom Navigation Bar setup for the Excursions app.
 * It defines the navigation bar and its items.
 */

/**
 * A composable function that sets up the bottom navigation bar for the app.
 *
 * @param navController The NavController that will be used to navigate between screens.
 */
@Composable
fun ExcursionsBottomBar(navController: NavHostController) {

    // Remember the selected item in the navigation bar across configuration changes
    var selectedItem by rememberSaveable { mutableIntStateOf(getSelectedIndex(navController.currentDestination?.route)) }

    // List of icons for the navigation bar items
    val navBarIcons = listOf(
        R.drawable.bottom_bar_icon_grid,
        R.drawable.bottom_bar_icon_favorite,
        R.drawable.bottom_bar_icon_profile)

    // Color for the selected item in the navigation bar
    val activeColor = OrangePolestar

    // Set up the navigation bar
    NavigationBar(
        modifier = Modifier
            .padding(5.dp)
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.White),
        containerColor = Color.White
    ) {
        // For each item in the navigation bar
        navBarIcons.forEachIndexed { index, item ->
            // Set up the navigation bar item
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    // When the item is clicked, update the selected item and navigate to the corresponding screen
                    selectedItem = index
                    val destination = when (index){
                        0 -> ExcursionsRoutes.Categories.route
                        1 -> ExcursionsRoutes.Favorites.route
                        2 -> ExcursionsRoutes.Profile.route
                        else -> {
                            "categories"
                        }
                    }
                    navController.navigate(destination)
                },
                icon = { Icon(
                    painter = painterResource(id = navBarIcons[index]),
                    contentDescription = null,
                    tint = if (selectedItem == index) activeColor else LocalContentColor.current
                ) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = GrayPolestar)

            )
        }
    }
}


fun getSelectedIndex(route: String?): Int {
    return when (route) {
        "categories" -> 0
        "favorites" -> 1
        "profile" -> 2
        else -> 0
    }
}


@Preview(showBackground = true, backgroundColor = 4294967295L)
@Composable
fun BottomAppPreview() {
    ExcursionsBottomBar(navController = rememberNavController())
}