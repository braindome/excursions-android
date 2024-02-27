package com.example.excursions.api

import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.api_models.SearchNearbyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface ExcursionsAPI {

    @POST
    fun searchNearbyPlaces(
        @Url url: String,
        @Body request: SearchNearbyRequest
    ): Call<SearchNearbyResponse>

    // Doesn't work with colon in endpoint string, issue with Retrofit
    @POST("places%3AsearchNearby")
    fun searchNearbyPlaces__(
        @Body request: SearchNearbyRequest
    ): Call<SearchNearbyResponse>
}