package com.example.excursions.data.repository

import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.api_models.SearchNearbyResponse
import retrofit2.Call

class DummyExcursionsAPI : ExcursionsAPI {
    override fun searchNearbyPlaces(
        url: String,
        request: SearchNearbyRequest
    ): Call<SearchNearbyResponse> {
        TODO("Not yet implemented")
    }

    override fun searchNearbyPlaces__(request: SearchNearbyRequest): Call<SearchNearbyResponse> {
        TODO("Not yet implemented")
    }
}