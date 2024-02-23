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
import com.example.excursions.ui.screens.LoginScreen
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
            //MyApp()
            LoginScreen()

            /*
            ExcursionsTheme {
                Surface(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        ElevatedButton(onClick = { getNearbyPlaces() }) {
                            Text(
                                text = "Get Nearby Places",
                                fontFamily = polestarFontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }
                }
            }

            */
        }


    }

    private fun getPlacesWithingRadius(coordinates: LatLng, radiusInMeters: Int) {
        Places.initializeWithNewPlacesApiEnabled(application, apiKey)

        val placesClient = Places.createClient(application)
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.TYPES)

    }

    private fun getNearbyPlaces() {
        Places.initializeWithNewPlacesApiEnabled(application, apiKey)

        val placesClient = Places.createClient(application)
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.TYPES)


        // Use the builder to create a FindCurrentPlaceRequest.
        val request: FindCurrentPlaceRequest = FindCurrentPlaceRequest.newInstance(placeFields)


        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {

            val placeResponse = placesClient.findCurrentPlace(request)

            placeResponse.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val response = task.result
                    for (placeLikelihood: PlaceLikelihood in response?.placeLikelihoods ?: emptyList()) {
                        // Timber.i("Place '" + placeLikelihood.place.name + "' has likelihood: " + placeLikelihood.likelihood)
                        Timber.i("Place found: ${placeLikelihood.place.name} with id ${placeLikelihood.place.id}")
                    }
                } else {
                    val exception = task.exception
                    if (exception is ApiException) {
                        Timber.e("Place not found: " + exception.statusCode)
                    }
                }
            }
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            //getLocationPermission()
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }
    }


}


