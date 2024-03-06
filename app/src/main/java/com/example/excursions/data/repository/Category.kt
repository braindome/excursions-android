package com.example.excursions.data.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var id: String = "",
    var name: String = "",
    var types: List<String> = emptyList()
) : Parcelable

