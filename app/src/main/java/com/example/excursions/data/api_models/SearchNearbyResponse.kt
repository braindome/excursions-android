package com.example.excursions.data.api_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchNearbyResponse(
    @Json(name = "places") val places: List<Place>
)

@JsonClass(generateAdapter = true)
data class Place(
    @Json(name = "displayName") val displayName: DisplayName,
    @Json(name = "formattedAddress") val formattedAddress: String,
    @Json(name = "id") val id: String,
    @Json(name = "location") val location: Location,
    @Json(name = "primaryType") val primaryType: String?,
    @Json(name = "types") val types: List<String>
)

@JsonClass(generateAdapter = true)
data class DisplayName(
    @Json(name = "languageCode") val languageCode: String,
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double
)