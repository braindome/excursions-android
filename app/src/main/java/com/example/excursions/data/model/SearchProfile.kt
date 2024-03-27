package com.example.excursions.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SearchProfile(
    val title : String = "",        // To Firestore
    val id: Int = 0,                // To Firestore
    val types: @RawValue MutableList<LocationType> = mutableListOf(), // To Firestore
    val range: Float = 50000f,
    val lat: Double? = null,
    val lng: Double? = null,
    val savedDestinations: @RawValue MutableList<PlaceState> = mutableListOf()  // To Firestore
) : Parcelable
