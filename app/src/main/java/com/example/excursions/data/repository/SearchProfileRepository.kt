package com.example.excursions.data.repository

import androidx.compose.ui.text.capitalize
import java.util.InputMismatchException

class SearchProfileRepository {

    val formattedOutdoorAdventure = formatListForUi(outdoorAdventure)
    companion object {
        val outdoorAdventure = listOf(
            "hiking_area",
            "national_park",
            "campground",
            "ski_resort",
            "marina",
            "dog_park",
            "farmstay",
            "rv_park",
            "extended_stay_hotel"
        )

        val culturalExploration = listOf(
            "art_gallery",
            "museum",
            "performing_arts_theater",
            "cultural_center",
            "historical_landmark",
            "library",
            "university"
        )

        val landmarkDiscovery = listOf(
            "historical_landmark",
            "museum",
            "tourist_attraction",
            "national_park",
            "visitor_center",
            "city_hall",
            "embassy"
        )

        val relaxationAndWellness = listOf(
            "spa",
            "resort_hotel",
            "bed_and_breakfast",
            "private_guest_room",
            "extended_stay_hotel"
        )

        val entertainmentHub = listOf(
            "amusement_park",
            "casino",
            "night_club",
            "movie_theater",
            "sports_club",
            "bowling_alley"
        )

        val carServices = listOf(
            "gas_station",
            "car_dealer",
            "car_rental",
            "car_repair",
            "car_wash",
            "parking",
            "rest_stop"
        )

        fun formatListForUi(list: List<String>): List<String> {
            return list.map { formatStringForUI(it) }
        }

        private fun formatStringForUI(input: String): String {
            val words = input.split("_").map { it.capitalize() }

            return words.joinToString(" ")
        }
    }
}