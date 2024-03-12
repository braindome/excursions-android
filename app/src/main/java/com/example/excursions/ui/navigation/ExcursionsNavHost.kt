package com.example.excursions.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.ui.screens.AuthenticationScreen
import com.example.excursions.ui.screens.CategoryScreen
import com.example.excursions.ui.screens.EditSearchProfileScreen
import com.example.excursions.ui.screens.FavoriteScreen
import com.example.excursions.ui.screens.IntroScreen
import com.example.excursions.ui.screens.LoginScreen
import com.example.excursions.ui.screens.ProfileScreen
import com.example.excursions.ui.screens.SavedDestinationsScreen
import com.example.excursions.ui.screens.SearchScreen
import com.example.excursions.ui.screens.SwipeScreen

@Composable
fun ExcursionsNavHost(viewModel: ExcursionsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ExcursionsRoutes.Intro.route) {
        composable(ExcursionsRoutes.Intro.route) { IntroScreen(navController = navController) }
        composable(ExcursionsRoutes.Authentication.route) { AuthenticationScreen(navController = navController) }
        //composable("createAccount") { CreateAccountScreen(navController, viewModel) }
        composable(ExcursionsRoutes.Login.route) { LoginScreen(navController) }
        composable(ExcursionsRoutes.Categories.route) { CategoryScreen(navController = navController, viewModel = viewModel) }
        composable(ExcursionsRoutes.Search.route) { SearchScreen(navController = navController) }
        composable(ExcursionsRoutes.Favorites.route) { SavedDestinationsScreen(navController = navController) }
        composable(ExcursionsRoutes.Profile.route) { ProfileScreen(navController = navController, viewModel = viewModel) }

        composable(
            route = ExcursionsRoutes.SwipeScreen.route + "/{placeListId}",
            arguments = listOf(navArgument("placeListId") { type = NavType.StringType })
        ) {backStackEntry ->
            val placeListId: String = backStackEntry.arguments?.getString("placeListId") ?: "no id"
            backStackEntry.arguments?.getString("placeListId")?.let {
                SwipeScreen(
                    navController = navController,
                    viewModel = viewModel,
                    placeListId = placeListId)
            }
        }

        composable(
            route = ExcursionsRoutes.EditSearchProfile.route + "/{searchProfileId}",
            arguments = listOf(navArgument("searchProfileId") {type = NavType.IntType})
        ) { backStackEntry ->
            val searchProfileId: Int = backStackEntry.arguments?.getInt("searchProfileId") ?: -1
            backStackEntry.arguments?.getInt("searchProfileId")?.let {
                EditSearchProfileScreen(
                    navController = navController,
                    viewModel = viewModel,
                    searchProfileId = searchProfileId
                )
            }
        }

        composable(ExcursionsRoutes.Saved.route) { FavoriteScreen(navController = navController) }

    }
}
enum class ExcursionsRoutes(val route: String) {
    Intro("intro"),
    Authentication("auth"),
    Login("login"),
    Categories("categories"),
    Search("search"),
    Favorites("favorites"),
    Profile("profile"),
    SwipeScreen("swipeScreen"),
    EditSearchProfile("addSearchProfile"),
    Saved("saved")
}