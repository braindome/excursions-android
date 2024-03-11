package com.example.excursions

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.Circle
import com.example.excursions.data.api_models.LocationRestriction
import com.example.excursions.data.api_models.Place
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.repository.LocationRepository
import com.example.excursions.data.repository.SearchProfileRepository
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.RuntimeException
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class ExcursionsViewModel(
    private val appContext: Context,
    private val api: ExcursionsAPI
) : ViewModel() {

    private val _searchProfilesList = MutableStateFlow<List<SearchProfile>>(emptyList())
    val searchProfilesList: StateFlow<List<SearchProfile>> = _searchProfilesList.asStateFlow()

    private val _resultPlaceList = MutableStateFlow<MutableList<Place>>(mutableListOf())
    val resultPlaceList: StateFlow<MutableList<Place>> = _resultPlaceList.asStateFlow()

    private val _searchProfile = MutableStateFlow(SearchProfile())
    val searchProfile: StateFlow<SearchProfile> = _searchProfile.asStateFlow()

    private val locationRepository = LocationRepository(appContext)

    private val _location = MutableLiveData<Center?>()
    val location: LiveData<Center?> get() = _location

    init {
        _resultPlaceList.value = mutableListOf()
        _searchProfilesList.value = SearchProfileRepository.defaultSearchProfiles
    }

    fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0 // Radius of the Earth in kilometers

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }

    fun distanceBetweenCenters(center1: Center, center2: Center): Double {
        return haversine(center1.latitude, center1.longitude, center2.latitude, center2.longitude)
    }

    fun fetchUserLocation() {
        viewModelScope.launch {
            try {
                val fetchedLocation = locationRepository.fetchLocation()
                val locationData = fetchedLocation?.let {
                    //LocationData(it.latitude, it.longitude)
                    Center(it.latitude, it.longitude)
                }
                _location.value = locationData
                Timber.d("Current location (vm): $location")
            } catch (e: Exception) {
                Timber.e(e, "Error fetching location")
            }
        }
    }


    fun updateLocationTypes(searchProfileID: Int, locationTypeID: Int, isChecked: Boolean) {
        val updatedProfiles = _searchProfilesList.value.map { searchProfile ->
            if (searchProfile.id == searchProfileID) {
                val updatedTypes = searchProfile.types.map { locationType ->
                    if (locationType.id == locationTypeID) {
                        locationType.copy(isChecked = isChecked)
                    } else {
                        locationType
                    }
                }
                searchProfile.copy(types = updatedTypes.toMutableList())
            } else {
                searchProfile
            }
        }
        _searchProfilesList.value = updatedProfiles
    }

    fun getSearchProfileById(searchProfileId: Int): SearchProfile {
        return searchProfilesList.value.find { it.id == searchProfileId } ?: SearchProfile()
    }


    fun updateSearchProfilesList(updatedSearchProfiles: List<SearchProfile>) {
        _searchProfilesList.value = updatedSearchProfiles
    }

    fun updateSearchProfileName(searchProfileId: Int, newName: String) {
        val updatedProfiles = _searchProfilesList.value.map {
            if (it.id == searchProfileId) {
                it.copy(name = newName)
            } else {
                it
            }
        }
        _searchProfilesList.value = updatedProfiles
    }

    fun updateSearchProfileUiState(updatedSearchProfile: SearchProfile) {
        _searchProfile.value = updatedSearchProfile
    }

    /**
     * POST-request
     * Fetches places in defined range from coordinates, corresponding
     * to defined types. Max number of results defined.
     * Using entire request url in the a string,
     * Retrofit can't deal with colons in endpoint string.
     */

    fun searchPlacesByLocationAndRadius(center: Center, types: List<String>, range: Float) {
        val updatedPlaces = _resultPlaceList.value
        Timber.d("Received range value: $range")
        val locationRestriction = LocationRestriction(Circle(center, range.toDouble()))
        val maxResultCount = 20
        val requestUrl = "https://places.googleapis.com/v1/places:searchNearby"

        val request = SearchNearbyRequest(
            types,
            locationRestriction,
            maxResultCount
        )

        Timber.d("Request: ${request.toString()}")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                Timber.d("Before result")
                val result = api.searchNearbyPlaces(requestUrl, request).execute()
                Timber.d("API response: ${result.raw().toString()}")

                if (result.isSuccessful) {
                    Timber.d("API response in if block: ${result.body()}")

                    val searchNearbyResponse = result.body()
                    if (searchNearbyResponse?.places != null && searchNearbyResponse.places.isNotEmpty()) {
                        Timber.d("Successful API call - API Response: ${result.body()}")
                        searchNearbyResponse.places.forEachIndexed { index, place ->
                            Timber.d("Place #$index: $place")
                            updatedPlaces.add(place)
                        }
                        _resultPlaceList.value = updatedPlaces

                    } else {
                        Timber.e("Error: 'places' field missing in the api response")
                        Timber.e("Full API Response: ${result.raw().toString()}")
                        Timber.e("Result error body: ${result.errorBody()}")
                    }

                } else {
                    Timber.e("Request failed with code ${result.code()}")
                    Timber.e("Error message: ${result.message()}")

                }
            } catch (e: JsonDataException) {
                Timber.e("JsonDataException during API call: ${e.message}")
                Timber.e("Cause: ${e.cause}")
            } catch (e: Exception) {
                Timber.e("Exception during API call: ${e.message}")
                Timber.e("Cause: ${e.cause}")
                e.printStackTrace()
            }
        }


    }

    fun searchPlacesByLocationAndRadius_old(center: Center, searchProfile: SearchProfile) {
        Timber.d("Search by searchprofile")
        val typeStrings = searchProfile.types.map { it.jsonName }
        val locationRestriction = LocationRestriction(Circle(center, searchProfile.range.toDouble()))
        val maxResultCount = 20
        val requestUrl = "https://places.googleapis.com/v1/places:searchNearby"


        val request = SearchNearbyRequest(
            typeStrings,
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
    fun updateSearchProfileSliderPosition(searchProfile: SearchProfile, sliderPosition: Float) {
        val updatedProfiles = _searchProfilesList.value.map {
            if (it.id == searchProfile.id) {
                it.copy(range = sliderPosition)
            } else {
                it
            }
        }
        _searchProfilesList.value = updatedProfiles
    }
}

/**
 * VM Factory because vm needs a constructor
 */

class ExcursionsViewModelFactory(private val appContext: Context, private val api: ExcursionsAPI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExcursionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExcursionsViewModel(appContext, api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}