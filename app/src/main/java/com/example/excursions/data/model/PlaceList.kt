package com.example.excursions.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.UUID

@Parcelize
data class PlaceList(
    val id: String = UUID.randomUUID().toString(),
    val list: @RawValue MutableList<PlaceState> = mutableListOf()
) : Parcelable
