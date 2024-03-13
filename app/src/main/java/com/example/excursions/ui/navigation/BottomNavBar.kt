package com.example.excursions.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.OrangePolestar

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier
) {
    var isGridPressed by remember { mutableStateOf(false) }
    var isSearchPressed by remember { mutableStateOf(false) }
    var isFavoritePressed by remember { mutableStateOf(false) }
    var isProfilePressed by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(5.dp)
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // TODO -



        val activeColor = OrangePolestar

        IconButton(
            onClick = {
                isGridPressed = !isGridPressed
                isSearchPressed = false
                isProfilePressed = false
                isFavoritePressed = false
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.bottom_bar_icon_grid),
                contentDescription = null,
                tint = if (isGridPressed) activeColor else LocalContentColor.current
            )
        }
        IconButton(
            onClick = {
                isSearchPressed = !isSearchPressed
                isGridPressed = false
                isProfilePressed = false
                isFavoritePressed = false
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.bottom_bar_icon_search),
                contentDescription = null,
                tint = if (isSearchPressed) activeColor else LocalContentColor.current
            )
        }
        IconButton(
            onClick = {
                isFavoritePressed = !isFavoritePressed
                isSearchPressed = false
                isGridPressed = false
                isProfilePressed = false
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.bottom_bar_icon_favorite),
                contentDescription = null,
                tint = if (isFavoritePressed) activeColor else LocalContentColor.current
            )
        }
        IconButton(
            onClick = {
                isProfilePressed = !isProfilePressed
                isSearchPressed = false
                isGridPressed = false
                isFavoritePressed = false
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.bottom_bar_icon_profile),
                contentDescription = null,
                tint = if (isProfilePressed) activeColor else LocalContentColor.current
            )
        }

    }


}

@Composable
fun ExcursionsBottomBar(navController: NavHostController) {

    var selectedItem by rememberSaveable { mutableIntStateOf(getSelectedIndex(navController.currentDestination?.route)) }
    val navBarIcons = listOf(
        R.drawable.bottom_bar_icon_grid,
        R.drawable.bottom_bar_icon_search,
        R.drawable.bottom_bar_icon_favorite,
        R.drawable.bottom_bar_icon_profile)
    val activeColor = OrangePolestar

    NavigationBar(
        modifier = Modifier
            .padding(5.dp)
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.White),
        containerColor = Color.White
    ) {
        navBarIcons.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    val destination = when (index){
                        0 -> ExcursionsRoutes.Categories.route
                        1 -> ExcursionsRoutes.Search.route
                        2 -> ExcursionsRoutes.Favorites.route
                        3 -> ExcursionsRoutes.Profile.route
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
        "search" -> 1
        "favorites" -> 2
        "profile" -> 3
        else -> 0
    }
}



@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = rememberNavController(), modifier = Modifier)
}

@Preview(showBackground = true, backgroundColor = 4294967295L)
@Composable
fun BottomAppPreview() {
    ExcursionsBottomBar(navController = rememberNavController())
}