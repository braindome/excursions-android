package com.example.excursions.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationType(
    val id : Int = 0,
    val jsonName: String = "",
    val formattedName: String = "",
    val isChecked: Boolean = true
) : Parcelable
