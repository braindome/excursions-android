package com.example.excursions.data.api_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchNearbyResponse(
    @Json(name = "places") val places: List<Place>
)

@JsonClass(generateAdapter = true)
data class Place(
    @Json(name = "displayName") val displayName: DisplayName = DisplayName("", ""),
    @Json(name = "formattedAddress") val formattedAddress: String = "",
    @Json(name = "id") val id: String = "",
    @Json(name = "location") val location: Location = Location(),
    @Json(name = "primaryType") val primaryType: String = "",
    @Json(name = "reviews") val reviews: List<Review>?,
    @Json(name = "types") val types: List<String>,
    @Json(name = "photos") val photos: List<Photo>? = null
)

@JsonClass(generateAdapter = true)
data class DisplayName(
    @Json(name = "languageCode") val languageCode: String = "",
    @Json(name = "text") val text: String = ""
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "latitude") val latitude: Double = 0.00,
    @Json(name = "longitude") val longitude: Double = 0.00
)

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "authorAttributions") val authorAttributions: List<AuthorAttribution>? = null,
    @Json(name = "heightPx") val heightPx: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "widthPx") val widthPx: Int = 0
)

@JsonClass(generateAdapter = true)
data class AuthorAttribution(
    @Json(name = "displayName") val displayName: String = "",
    @Json(name = "photoUri") val photoUri: String = "",
    @Json(name = "uri") val uri: String = ""
)

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "authorAttribution") val authorAttribution: AuthorAttribution? = null,
    @Json(name = "name") val name: String = "",
    @Json(name = "originalText") val originalText: OriginalText? = null,
    @Json(name = "publishTime") val publishTime: String = "",
    @Json(name = "rating") val rating: Int = 0,
    @Json(name = "relativePublishTimeDescription") val relativePublishTimeDescription: String = "",
    @Json(name = "text") val text: Text? = null
)

@JsonClass(generateAdapter = true)
data class OriginalText(
    @Json(name = "languageCode") val languageCode: String = "",
    @Json(name = "text") val text: String = ""
)

@JsonClass(generateAdapter = true)
data class Text(
    @Json(name = "languageCode") val languageCode: String = "",
    @Json(name = "text") val text: String = ""
)