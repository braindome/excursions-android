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

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "authorAttributions") val authorAttributions: List<AuthorAttribution>,
    @Json(name = "heightPx") val heightPx: Int,
    @Json(name = "name") val name: String,
    @Json(name = "widthPx") val widthPx: Int
)

@JsonClass(generateAdapter = true)
data class AuthorAttribution(
    @Json(name = "displayName") val displayName: String,
    @Json(name = "photoUri") val photoUri: String,
    @Json(name = "uri") val uri: String
)

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "authorAttribution") val authorAttribution: AuthorAttribution,
    @Json(name = "name") val name: String,
    @Json(name = "originalText") val originalText: OriginalText,
    @Json(name = "publishTime") val publishTime: String,
    @Json(name = "rating") val rating: Int,
    @Json(name = "relativePublishTimeDescription") val relativePublishTimeDescription: String,
    @Json(name = "text") val text: Text
)

@JsonClass(generateAdapter = true)
data class OriginalText(
    @Json(name = "languageCode") val languageCode: String,
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class Text(
    @Json(name = "languageCode") val languageCode: String,
    @Json(name = "text") val text: String
)