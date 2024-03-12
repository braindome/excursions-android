package com.example.excursions.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocationRepository(private val appContext: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            appContext,
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

                        // Update your SearchProfile with the new coordinates
                        //val updatedState = currentState.updateCoordinates(latitude, longitude)
                        // Do something with the updated state
                    }
                }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun fetchLocation(): Location? = suspendCoroutine { continuation ->
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
            Timber.d("Current location: $location")
        }.addOnFailureListener { exception ->
            continuation.resumeWithException(exception)
        }
    }
}