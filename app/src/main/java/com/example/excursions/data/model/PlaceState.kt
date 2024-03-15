package com.example.excursions.data.model

import android.os.Parcelable
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
import com.example.excursions.data.api_models.Review
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PlaceState(
    val displayName: @RawValue DisplayName = DisplayName("", ""),
    val formattedAddress: String = "",
    val id: String = "",
    val location: @RawValue Location = Location(0.00,0.00),
    val primaryType: String? = "",
    val types: List<String> = listOf(),
    val reviews: @RawValue List<Review>? = listOf(),
    val isFavorite: Boolean = false,
    val isDiscarded: Boolean = false
) : Parcelable

fun List<Review>.calculateRatingAverage(): Double {
    if (isEmpty()) {
        return 0.0
    }

    val sum = fold(0) { acc, review -> acc + review.rating }
    return sum.toDouble() / size
}

// Extension function for PlaceState
fun PlaceState.calculateRatingAverage(): Double {
    return reviews?.calculateRatingAverage() ?: 0.0
}
