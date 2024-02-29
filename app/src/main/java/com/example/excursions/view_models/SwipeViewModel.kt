package com.example.excursions.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.Circle
import com.example.excursions.data.api_models.LocationRestriction
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.repository.PlaceRepository
import com.example.excursions.data.ui_models.SwipeCardPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SwipeViewModel(private val api: ExcursionsAPI) : ViewModel() {

    val placeRepository = PlaceRepository(api)
    private val cardList = getLocationList()

    private fun getLocationList(): List<SwipeCardPlace> {
        var swipeCardPlaceList = listOf<SwipeCardPlace>()
        viewModelScope.launch(Dispatchers.IO) {
            swipeCardPlaceList = placeRepository.searchNearbyPlaces()
        }
        return swipeCardPlaceList

    }
}