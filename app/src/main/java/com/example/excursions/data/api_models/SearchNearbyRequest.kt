package com.example.excursions.data.api_models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchNearbyRequest(
    val includedTypes: List<String>,
    val locationRestriction: LocationRestriction,
    val maxResultCount: Int
)

@JsonClass(generateAdapter = true)
data class LocationRestriction(
    val circle: Circle
)

@JsonClass(generateAdapter = true)
data class Circle(
    val center: Center,
    val radius: Double
)

@JsonClass(generateAdapter = true)
data class Center(
    val latitude: Double,
    val longitude: Double
)

fun Location.toCenter(): Center {
    return Center(latitude, longitude)
}
