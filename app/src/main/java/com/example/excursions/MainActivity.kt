package com.example.excursions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ui.screens.AuthenticationScreen
import com.example.excursions.ui.screens.CategoryScreen
import com.example.excursions.ui.screens.FavoriteScreen
import com.example.excursions.ui.screens.LoginScreen
import com.example.excursions.ui.screens.ProfileScreen
import com.example.excursions.ui.screens.SearchScreen
import com.example.excursions.ui.theme.ExcursionsTheme
import com.example.excursions.ui.theme.polestarFontFamily
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceLikelihood
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import timber.log.Timber

const val apiKey = BuildConfig.PLACES_API_KEY

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
            }
        }


    }



}


