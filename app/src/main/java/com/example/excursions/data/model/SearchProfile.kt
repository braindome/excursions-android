package com.example.excursions.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/*
@Parcelize
data class SearchProfile(
    val id: Int = 0,
    val category: @RawValue Category = Category("", "", listOf()),
    val range: Float = 50f,
    val lat: Double = 0.00,
    val lng: Double = 0.00
) : Parcelable

 */

@Parcelize
data class SearchProfile(
    val name : String = "",
    val id: Int = 0,
    val types: @RawValue MutableList<LocationType> = mutableListOf(),
    val range: Float = 50000f,
    val lat: Double = 0.00,
    val lng: Double = 0.00
) : Parcelable
