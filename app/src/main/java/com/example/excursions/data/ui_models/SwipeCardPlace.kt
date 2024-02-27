package com.example.excursions.data.ui_models

import android.location.Location


class SwipeCardPlace(
    val id: String,
    val name: String,
    val coordinates: Location,
    var distance: Int?,
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
) {
    fun calculateDistanceFromCurrentLocation(currentLocation: Location): Int {
        val result = FloatArray(1)
        Location.distanceBetween(
            currentLocation.latitude,
            currentLocation.longitude,
            coordinates.latitude,
            coordinates.longitude,
            result
        )

        val distanceInMeters = result[0]
        val distanceInKilometers = distanceInMeters / 1000.0

        return distanceInKilometers.toInt()
    }
}