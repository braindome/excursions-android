package com.example.excursions.data.model

import android.os.Parcelable
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
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
    val isFavorite: Boolean = false,
) : Parcelable
