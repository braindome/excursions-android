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
import com.example.excursions.ui.screens.MapScreen
import com.example.excursions.ui.screens.PlaceDetailScreen
import com.example.excursions.ui.screens.ProfileScreen
import com.example.excursions.ui.screens.SavedDestinationDetailScreen
import com.example.excursions.ui.screens.SavedDestinationsScreen
import com.example.excursions.ui.screens.SearchScreen
import com.example.excursions.ui.screens.SwipeScreen

/**
 * This file contains the navigation setup for the Excursions app.
 * It defines the navigation routes and their corresponding screens.
 * Each route is associated with a composable function that represents a screen in the app.
 */

/**
 * A composable function that sets up the navigation for the app.
 *
 * @param viewModel The ViewModel that will be passed to the screens that need it.
 */

@Composable
fun ExcursionsNavHost(viewModel: ExcursionsViewModel) {
    // Create a NavController to navigate between screens
    val navController = rememberNavController()

    // Set up the navigation host with the start destination
    NavHost(navController = navController, startDestination = ExcursionsRoutes.Intro.route) {
        // Define the composable for each route
        composable(ExcursionsRoutes.Intro.route) { IntroScreen(navController = navController) }
        composable(ExcursionsRoutes.Authentication.route) { AuthenticationScreen(navController = navController) }
        //composable("createAccount") { CreateAccountScreen(navController, viewModel) }
        composable(ExcursionsRoutes.Login.route) { LoginScreen(navController) }
        composable(ExcursionsRoutes.Categories.route) { CategoryScreen(navController = navController, viewModel = viewModel) }
        composable(ExcursionsRoutes.Search.route) { SearchScreen(navController = navController) }
        composable(ExcursionsRoutes.Favorites.route) { SavedDestinationsScreen(navController = navController, viewModel = viewModel) }
        composable(ExcursionsRoutes.Profile.route) { ProfileScreen(navController = navController, viewModel = viewModel) }

        /**
         * Define the composable for each route with arguments. The arguments are passed to the composable
         * function as a parameter. The arguments are defined using the navArgument function.
         */

        composable(
            route = ExcursionsRoutes.SwipeScreen.route + "/{placeListId}/{searchProfileId}",
            arguments = listOf(
                navArgument("placeListId") { type = NavType.StringType },
                navArgument("searchProfileId") { type = NavType.IntType }
            )
        ) {backStackEntry ->
            val placeListId: String = backStackEntry.arguments?.getString("placeListId") ?: "no id"
            val searchProfileId: Int = backStackEntry.arguments?.getInt("searchProfileId") ?: -1
            backStackEntry.arguments?.getString("placeListId")?.let {
                SwipeScreen(
                    navController = navController,
                    viewModel = viewModel,
                    placeListId = placeListId,
                    searchProfileId = searchProfileId
                )
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

        composable(
            route = ExcursionsRoutes.Saved.route + "/{searchProfileId}",
            arguments = listOf(navArgument("searchProfileId") {type = NavType.IntType})
        ) {backStackEntry ->
            val searchProfileId: Int = backStackEntry.arguments?.getInt("searchProfileId") ?: -1
            backStackEntry.arguments?.getInt("searchProfileId")?.let {
                FavoriteScreen(
                    navController = navController,
                    viewModel = viewModel,
                    searchProfileId = searchProfileId
                )
            }
        }

        composable(
            route = ExcursionsRoutes.PlaceDetailScreen.route + "/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.StringType } )
        ) {backStackEntry ->
            val placeId: String = backStackEntry.arguments?.getString("placeId") ?: "no id"
            backStackEntry.arguments?.getString("placeId")?.let {
                PlaceDetailScreen(
                    navController = navController,
                    placeId = placeId,
                    viewModel = viewModel
                )
            }

        }

        composable(
            route = ExcursionsRoutes.Map.route + "/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.StringType } )
        ) {backStackEntry ->
            val placeId: String = backStackEntry.arguments?.getString("placeId") ?: "no id"
            backStackEntry.arguments?.getInt("placeId")?.let {
                MapScreen(
                    placeId = placeId,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }

        composable(
            route = ExcursionsRoutes.SavedDestinationDetailScreen.route + "/{searchProfileId}/{placeId}",
            arguments = listOf(
                navArgument("searchProfileId") { type = NavType.IntType },
                navArgument("placeId") { type = NavType.StringType }
            )
        ) {backStackEntry ->
            val searchProfileId: Int = backStackEntry.arguments?.getInt("searchProfileId") ?: -1
            val placeId: String = backStackEntry.arguments?.getString("placeId") ?: "no id"
            SavedDestinationDetailScreen(
                navController = navController,
                viewModel = viewModel,
                placeId = placeId,
                searchProfileId = searchProfileId
            )
        }
    }
}

/**
 * Enum class that defines the routes for the app's navigation.
 * Each route is associated with a string that is used in the navigation graph.
 */
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
    PlaceDetailScreen("placeDetailScreen"),
    SavedDestinationDetailScreen("savedDestinationDetailScreen"),
    Saved("saved"),
    Map("map")
}