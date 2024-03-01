package com.example.excursions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.Circle
import com.example.excursions.data.api_models.LocationRestriction
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.ui.screens.AddSearchProfileScreen
import com.example.excursions.ui.screens.AuthenticationScreen
import com.example.excursions.ui.screens.CategoryScreen
import com.example.excursions.ui.screens.FavoriteScreen
import com.example.excursions.ui.screens.IntroScreen
import com.example.excursions.ui.screens.LoginScreen
import com.example.excursions.ui.screens.ProfileScreen
import com.example.excursions.ui.screens.SavedDestinationsScreen
import com.example.excursions.ui.screens.SearchScreen
import com.example.excursions.ui.screens.SwipeScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

const val apiKey = BuildConfig.PLACES_API_KEY

enum class ExcursionsRoutes(val route: String) {
    Intro("intro"),
    Authentication("auth"),
    MainActivity("mainActivity"),
    Login("login"),
    Categories("categories"),
    Search("search"),
    Favorites("favorites"),
    Profile("profile"),
    SwipeScreen("swipeScreen"),
    AddSearchProfile("addSearchProfile"),
    Saved("saved")
}

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ExcursionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create ViewModelFactory with the ExcursionsAPI instance
        val viewModelFactory = ExcursionsViewModelFactory((application as ExcursionsApp).api)

        // Initialize ViewModel using ViewModelProvider with the factory
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExcursionsViewModel::class.java)

        //viewModel.searchPlacesByLocationAndRadius()

        /*

        /**
         * POST-request
         * Fetches places in defined range from coordinates, corresponding
         * to defined types. Max number of results defined.
         * Using entire request url in the a string,
         * Retrofit can't deal with colons in endpoint string.
         */

        val excursionsApp = application as ExcursionsApp
        val includedTypes = listOf("hiking_area", "national_park", "campground", "ski_resort", "marina", "dog_park", "farmstay", "rv_park", "extended_stay_hotel")
        val locationRestriction = LocationRestriction(Circle(Center(58.2726, 11.6399), 10000.00))
        val maxResultCount = 20
        val requestUrl = "https://places.googleapis.com/v1/places:searchNearby"

        val request = SearchNearbyRequest(
            includedTypes,
            locationRestriction,
            maxResultCount
        )

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = excursionsApp.api.searchNearbyPlaces(requestUrl, request).execute()

                if (result.isSuccessful) {
                    val searchNearbyResponse = result.body()
                    //Timber.d("Successful call: $searchNearbyResponse")
                    Timber.d("Successful API call:")
                    searchNearbyResponse?.places?.forEachIndexed { index, place ->
                        Timber.d("Place #$index: $place")
                    }
                } else {
                    Timber.e("Request failed with code ${result.code()}")
                }
            } catch (e: Exception) {
                Timber.e("Exception during API call: ${e.message}")
            }
        }

        // Old request
        /*
        lifecycleScope.launch(Dispatchers.IO) {
            val result = excursionsApp.api.searchNearbyPlaces("https://places.googleapis.com/v1/places:searchNearby", request).execute()
            if (result.isSuccessful) {
                Timber.d("Successful call: $result")
            } else {
                Timber.e("Request failed with code ${result.code()}")
            }
        }

        */

         */

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = ExcursionsRoutes.Intro.route) {
                composable(ExcursionsRoutes.Intro.route) { IntroScreen(navController = navController) }
                composable(ExcursionsRoutes.Authentication.route) { AuthenticationScreen(navController = navController) }
                //composable("createAccount") { CreateAccountScreen(navController, viewModel) }
                composable(ExcursionsRoutes.MainActivity.route) { AppScreen() }
                composable(ExcursionsRoutes.Login.route) { LoginScreen(navController) }
                composable(ExcursionsRoutes.Categories.route) { CategoryScreen(navController = navController) }
                composable(ExcursionsRoutes.Search.route) { SearchScreen(navController = navController) }
                composable(ExcursionsRoutes.Favorites.route) {
                    //FavoriteScreen(navController = navController) 
                    SavedDestinationsScreen(navController = navController)
                }
                composable(ExcursionsRoutes.Profile.route) { ProfileScreen(navController = navController) }
                composable(ExcursionsRoutes.SwipeScreen.route) { SwipeScreen(navController = navController) }
                composable(ExcursionsRoutes.AddSearchProfile.route) { AddSearchProfileScreen(navController = navController) }
                composable(ExcursionsRoutes.Saved.route) { FavoriteScreen(navController = navController) }

            }
        }


    }



}




