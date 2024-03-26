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
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.model.LocationType
import com.example.excursions.data.model.PlaceList
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.repository.LocationRepository
import com.example.excursions.data.repository.SearchProfileRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
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

    private val _resultPlaceList = MutableStateFlow(PlaceList())
    val resultPlaceList: StateFlow<PlaceList> = _resultPlaceList.asStateFlow()

    private val _favoritePlaceList = MutableStateFlow(PlaceList())
    val favoritePlaceList: StateFlow<PlaceList> = _favoritePlaceList.asStateFlow()

    private val _searchProfile = MutableStateFlow(SearchProfile())
    val searchProfile: StateFlow<SearchProfile> = _searchProfile.asStateFlow()

    private val _favoritePlace = MutableStateFlow<PlaceState?>(null)
    val favoritePlace: StateFlow<PlaceState?> = _favoritePlace.asStateFlow()

    private val locationRepository = LocationRepository(appContext)

    private val _location = MutableLiveData<Center?>()
    val location: LiveData<Center?> get() = _location
    val db = Firebase.firestore
    private val mainCollection = "antonio"
    val savedDestinationsPath = "savedDestinations"

    init {
        _resultPlaceList.value = PlaceList()
        //addDefaultProfilesToFirestore()
        loadProfilesFromFirestore()
    }

    // Run only if Firestore is empty
    private fun addDefaultProfilesToFirestore() {
        _searchProfilesList.value = listOf(
            SearchProfile(id = 1, title = "Outdoor Adventure", types = SearchProfileRepository.outdoorAdventure),
            SearchProfile(id = 2, title = "Cultural Exploration", types = SearchProfileRepository.culturalExploration),
            SearchProfile(id = 3, title = "Landmark Discovery", types = SearchProfileRepository.landmarkDiscovery),
            SearchProfile(id = 4, title = "Relaxation and Wellness", types = SearchProfileRepository.relaxationAndWellness),
            SearchProfile(id = 5, title = "Entertainment hub", types = SearchProfileRepository.entertainmentHub),
            SearchProfile(id = 6, title = "Car Services", types = SearchProfileRepository.carServices)
        )

        for (profile in _searchProfilesList.value) {
            val profileToFirestore = hashMapOf(
                "id" to profile.id,
                "title" to profile.title,
                "types" to profile.types
            )
            db.collection("antonio").document(profile.id.toString()).set(profileToFirestore)
        }
    }

    fun getFavoriteFromFirestore(placeId: String, searchProfileId: Int) {
        val docRef = db.collection(mainCollection)
            .document(searchProfileId.toString())
            .collection(savedDestinationsPath)
            .document(placeId)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Timber.d("Document snapshot data: ${document.data}")
                    val placeState = document.toObject<PlaceState>()
                    _favoritePlace.value = placeState
                } else {
                    Timber.w("No such document")
                }
            }
            .addOnFailureListener { exception ->
                Timber.e("get failed with ", exception)
                _favoritePlace.value = null
            }
    }

    fun listenForFavoriteChanges(searchProfileId: Int, onFavoritePlacesUpdate: (List<PlaceState>) -> Unit) {
        val docRef = db.collection(mainCollection)
            .document(searchProfileId.toString())
            .collection(savedDestinationsPath)

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Timber.e("Listen failed: $e")
                return@addSnapshotListener
            }

            val updatedFavoritePlaces = snapshot?.documents?.mapNotNull { document ->
                val placeState = document.toObject<PlaceState>()
                placeState?.copy(id = document.id)
            } ?: emptyList()
            //Timber.d("Updated favorite places from firestore: $updatedFavoritePlaces")
            onFavoritePlacesUpdate(updatedFavoritePlaces)
        }
    }

    fun removeDestinationFromFavorites(searchProfileId: Int, placeState: PlaceState) {
        val placeToSave = placeState.copy(isFavorite = false)
        val savedDestinationRef = db.collection("antonio")
            .document(searchProfileId.toString())
            .collection("savedDestinations")
            .document(placeState.id)

        savedDestinationRef
            .set(placeToSave)
            .addOnSuccessListener {
                Timber.d("Document successfully added to Firestore! ${placeToSave.displayName.text} to document with id ${placeToSave.id}")
            }
            .addOnFailureListener {
                    e -> Timber.d("Error adding document: $e")
            }
    }

    fun savePlaceToFirestore(searchProfileId: Int, placeState: PlaceState) {
        val placeToSave = placeState.copy(isFavorite = true)
        val savedDestinationRef = db.collection("antonio")
            .document(searchProfileId.toString())
            .collection("savedDestinations")
            .document(placeState.id)

        savedDestinationRef
            .set(placeToSave)
            .addOnSuccessListener {
                Timber.d("Document successfully added to Firestore! ${placeToSave.displayName.text} to document with id ${placeToSave.id}")
            }
            .addOnFailureListener {
                e -> Timber.d("Error adding document: $e")
            }
    }

    private fun loadProfilesFromFirestore() {
        viewModelScope.launch {
            val profileIds = listOf(1,2,3,4,5,6)
            val profiles = fetchSearchProfiles(profileIds)
            _searchProfilesList.value = profiles
        }
    }

    private suspend fun fetchSearchProfiles(profileIds: List<Int>): List<SearchProfile> {
        return coroutineScope {
            profileIds.map { id ->
                async(Dispatchers.IO) {
                    val document = db.collection("antonio").document(id.toString()).get().await()
                    if (document.exists()) {
                        val dataMap = document.data
                        //val types = document["types"] as MutableList<LocationType> // Assuming types is a List<String>
                        val types = mutableListOf<LocationType>()
                        val typesDataList = dataMap?.get("types") as? List<Map<String, Any>>
                        typesDataList?.forEach { typeData ->
                            Timber.d("$typeData")
                            val typeId = (typeData["id"] as Long).toInt()
                            val typeName = typeData["jsonName"] as String
                            val typeFormattedName = typeData["formattedName"] as String
                            val typeIsChecked = typeData["isChecked"] as Boolean
                            val locationType = LocationType(typeId, typeName, typeFormattedName, typeIsChecked)
                            types.add(locationType)
                        }
                        SearchProfile(
                            id = id,
                            title = document["title"] as String,
                            types = types,
                        )

                    } else {
                        null // Handle case where document doesn't exist
                    }
                }
            }.awaitAll().filterNotNull()
        }
    }

    fun getPlaceById(placeId: String): PlaceState {
        return resultPlaceList.value.list.find{ it.id == placeId } ?: PlaceState()

    }

    fun removeDestinationFromFavorites__(place: PlaceState, searchProfile: SearchProfile) {
        val docRef = db.collection(mainCollection)
            .document(searchProfile.id.toString())
            .collection(savedDestinationsPath)
            .document(place.id)

        // Remove the destination from Firestore
        docRef.delete()
            .addOnSuccessListener {
                Timber.d("DocumentSnapshot successfully deleted!")
                // Now update the local state list
                val updatedProfiles = _searchProfilesList.value.toMutableList()
                val index = updatedProfiles.indexOfFirst { it.id == searchProfile.id }
                if (index != -1) {
                    val placeToUpdate = updatedProfiles[index].savedDestinations.find { it.id == place.id }
                    if (placeToUpdate != null) {
                        val updatedPlace = placeToUpdate.copy(isFavorite = false) // Create a copy with modified isFavorite
                        updatedProfiles[index].savedDestinations[updatedProfiles[index].savedDestinations.indexOf(placeToUpdate)] = updatedPlace
                        _searchProfilesList.value = updatedProfiles
                        Timber.d("Marked place as not favorite: ${updatedPlace.displayName.text}")  // Log update
                    } else {
                        Timber.d("Place not found in profile")
                    }
                } else {
                    Timber.d("Profile not found")
                }
            }
            .addOnFailureListener { e ->
                Timber.w("Error deleting document: $e")
            }
    }

    fun discardDestination(searchProfileId: Int, placeState: PlaceState) {
        val updatedProfiles = _searchProfilesList.value.toMutableList()
        val index = updatedProfiles.indexOfFirst { it.id == searchProfileId }
        if (index != -1) {
            updatedProfiles[index].savedDestinations.add(placeState.copy(isDiscarded = true))
            _searchProfilesList.value = updatedProfiles
        } else {
            Timber.d("Profile not found")
        }
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
        val docRef = db.collection("antonio").document(searchProfileID.toString())
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

        docRef.update("types", updatedProfiles.find { it.id == searchProfileID }?.types)
            .addOnSuccessListener { Timber.d("Document updated") }
            .addOnFailureListener { e -> Timber.d("Error updating document: $e") }

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
                it.copy(title = newName)
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

    fun searchPlacesByLocationAndRadius(center: Center, types: List<String>, range: Float, placeListId: Int) {
        val updatedPlaces = _resultPlaceList.value.copy()
        Timber.d("Received range value: $range")
        val locationRestriction = LocationRestriction(Circle(center, range.toDouble()))
        val maxResultCount = 20
        val requestUrl = "https://places.googleapis.com/v1/places:searchNearby"

        val request = SearchNearbyRequest(
            types,
            locationRestriction,
            maxResultCount
        )
        //Timber.d("Request: ${request.toString()}")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Timber.d("Before result")
                val result = api.searchNearbyPlaces(requestUrl, request).execute()
                //Timber.d("API response: ${result.raw().toString()}")
                if (result.isSuccessful) {
                    //Timber.d("API response in if block: ${result.body()}")
                    val searchNearbyResponse = result.body()
                    if (searchNearbyResponse?.places != null && searchNearbyResponse.places.isNotEmpty()) {
                        val newPlacesList = searchNearbyResponse.places.map { place ->
                            PlaceState(
                                displayName = place.displayName,
                                formattedAddress = place.formattedAddress,
                                id = place.id,
                                location = place.location,
                                primaryType = place.primaryType,
                                types = place.types,
                                reviews = place.reviews,
                                photos = place.photos,
                                isFavorite = false,
                                isDiscarded = false
                            )
                        }
                        val newPlaceList = PlaceList(id = placeListId.toString(), list = newPlacesList.toMutableList())
                        _resultPlaceList.value = newPlaceList
                        Timber.d("Updated resultPlaceList id: ${newPlaceList.id}")
                    } else {
                        Timber.e("Error: 'places' field missing in the api response")
                    }

                } else {
                    Timber.e("Request failed with code ${result.code()}")
                }
            } catch (e: JsonDataException) {
                Timber.e("JsonDataException during API call: ${e.message}")
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