package com.example.excursions.data.repository

import android.location.Location
import androidx.lifecycle.lifecycleScope
import com.example.excursions.ExcursionsApp
import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.Circle
import com.example.excursions.data.api_models.LocationRestriction
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.ui_models.SwipeCardPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class PlaceRepository(private val api: ExcursionsAPI) {
    suspend fun searchNearbyPlaces(): List<SwipeCardPlace> {
        val includedTypes = listOf("hiking_area", "national_park", "campground", "ski_resort", "marina", "dog_park", "farmstay", "rv_park", "extended_stay_hotel")
        val locationRestriction = LocationRestriction(Circle(Center(58.2726, 11.6399), 10000.00))
        val maxResultCount = 20
        val requestUrl = "https://places.googleapis.com/v1/places:searchNearby"

        val request = SearchNearbyRequest(
            includedTypes,
            locationRestriction,
            maxResultCount
        )


        try {
            val result = api.searchNearbyPlaces(requestUrl, request).execute()

            if (result.isSuccessful) {
                val searchNearbyResponse = result.body()
                //Timber.d("Successful call: $searchNearbyResponse")
                Timber.d("Successful API call:")

                val destinationResults = mutableListOf<SwipeCardPlace>()

                searchNearbyResponse?.places?.forEachIndexed { index, place ->
                    Timber.d("Place #$index: $place")
                    val location = Location(null)
                    location.latitude = place.location.latitude
                    location.longitude = place.location.longitude
                    val swipeCardPlace = SwipeCardPlace(
                        id = place.id,
                        coordinates = location,
                        name = place.displayName.text,
                        distance = null
                    )
                    swipeCardPlace.distance = swipeCardPlace.calculateDistanceFromCurrentLocation(swipeCardPlace.coordinates)

                    destinationResults.add(swipeCardPlace)


                }

                return destinationResults
            } else {
                Timber.e("Request failed with code ${result.code()}")
            }
        } catch (e: Exception) {
            Timber.e("Exception during API call: ${e.message}")
        }

        return emptyList()


    }
}