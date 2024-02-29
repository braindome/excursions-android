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
import com.example.excursions.ui.screens.LoginScreen
import com.example.excursions.ui.screens.ProfileScreen
import com.example.excursions.ui.screens.SearchScreen
import com.example.excursions.ui.screens.SwipeScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

const val apiKey = BuildConfig.PLACES_API_KEY

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

            NavHost(navController = navController, startDestination = "login") {
                composable("auth") { AuthenticationScreen(navController = navController) }
                //composable("createAccount") { CreateAccountScreen(navController, viewModel) }
                composable("mainActivity") { AppScreen() }
                composable("login") { LoginScreen(navController) }
                composable("categories") { CategoryScreen(navController = navController) }
                composable("search") { SearchScreen(navController = navController) }
                composable("favorites") { FavoriteScreen(navController = navController) }
                composable("profile") { ProfileScreen(navController = navController) }
                composable("swipeScreen") { SwipeScreen(navController = navController) }
                composable("addSearchProfile") { AddSearchProfileScreen(navController = navController) }

            }
        }


    }



}




