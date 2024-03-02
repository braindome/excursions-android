package com.example.excursions

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.Circle
import com.example.excursions.data.api_models.LocationRestriction
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.repository.SearchProfileRepository
import com.google.android.libraries.places.api.Places
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ExcursionsViewModel(private val api: ExcursionsAPI) : ViewModel() {

    val searchProfileRepository = SearchProfileRepository

    fun searchPlacesByLocationAndRadius(center: Center, radius: Double, includedTypes: List<String>) {
        //val includedTypes = listOf("hiking_area", "national_park", "campground", "ski_resort", "marina", "dog_park", "farmstay", "rv_park", "extended_stay_hotel")
        //val locationRestriction = LocationRestriction(Circle(Center(58.2726, 11.6399), 10000.00))
        val locationRestriction = LocationRestriction(Circle(center, radius))
        val maxResultCount = 20
        val requestUrl = "https://places.googleapis.com/v1/places:searchNearby"

        val request = SearchNearbyRequest(
            includedTypes,
            locationRestriction,
            maxResultCount
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = api.searchNearbyPlaces(requestUrl, request).execute()

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
    }


}

class ExcursionsViewModelFactory(private val api: ExcursionsAPI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExcursionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExcursionsViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}