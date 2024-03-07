package com.example.excursions.data.model

import android.os.Parcelable
import com.example.excursions.data.repository.Category
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SearchProfile(
    val id: Int = 0,
    val category: @RawValue Category = Category("", "", listOf()),
    val range: Float = 0f,
    val lat: Double = 0.00,
    val lng: Double = 0.00
) : Parcelable
