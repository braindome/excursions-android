package com.example.excursions

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.excursions.ui.navigation.ExcursionsNavHost
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

const val apiKey = BuildConfig.PLACES_API_KEY


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ExcursionsViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    //

    //@SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (checkLocationPermission()) {
            requestLocationUpdates()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        val viewModelFactory = ExcursionsViewModelFactory(
            applicationContext,
            (application as ExcursionsApp).api
        )

        viewModel = ViewModelProvider(this, viewModelFactory)[ExcursionsViewModel::class.java]

        setContent {
            ExcursionsNavHost(viewModel = viewModel)
        }

    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationUpdates() {
        try {
            // Request last known location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        // Use the location data as needed
                        val latitude = location.latitude
                        val longitude = location.longitude
                        //val updatedState = currentState.updateCoordinates(latitude, longitude)
                    }
                }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}




