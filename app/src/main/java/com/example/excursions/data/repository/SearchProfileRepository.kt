package com.example.excursions.data.repository

import androidx.compose.ui.text.capitalize
import com.example.excursions.data.model.LocationType
import com.example.excursions.data.model.SearchProfile
import java.util.InputMismatchException

class SearchProfileRepository {



    companion object {
        val outdoorAdventure : List<LocationType> = createLocationTypes(
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

        val culturalExploration : List<LocationType> = createLocationTypes(
            "art_gallery",
            "museum",
            "performing_arts_theater",
            "cultural_center",
            "historical_landmark",
            "library",
            "university"
        )

        val landmarkDiscovery : List<LocationType> = createLocationTypes(
            "historical_landmark",
            "museum",
            "tourist_attraction",
            "national_park",
            "visitor_center",
            "city_hall",
            "embassy"
        )

        val relaxationAndWellness : List<LocationType> = createLocationTypes(
            "spa",
            "resort_hotel",
            "bed_and_breakfast",
            "private_guest_room",
            "extended_stay_hotel"
        )

        val entertainmentHub : List<LocationType> = createLocationTypes(
            "amusement_park",
            "casino",
            "night_club",
            "movie_theater",
            "sports_club",
            "bowling_alley"
        )

        val carServices : List<LocationType> = createLocationTypes(
            "gas_station",
            "car_dealer",
            "car_rental",
            "car_repair",
            "car_wash",
            "parking",
            "rest_stop"
        )

        val defaultTypeLists = listOf(
            outdoorAdventure, culturalExploration, landmarkDiscovery, relaxationAndWellness, entertainmentHub, carServices
        )

        val defaultSearchProfiles : List<SearchProfile> = listOf(
            SearchProfile(id = 1, name = "Outdoor Adventure", types = outdoorAdventure),
            SearchProfile(id = 2, name = "Cultural Exploration", types = culturalExploration),
            SearchProfile(id = 3, name = "Landmark Discovery", types = landmarkDiscovery),
            SearchProfile(id = 4, name = "Relaxation and Wellness", types = relaxationAndWellness),
            SearchProfile(id = 5, name = "Entertainment hub", types = entertainmentHub),
            SearchProfile(id = 6, name = "Car Services", types = carServices)
        )

        fun createLocationTypes(vararg names: String): List<LocationType> {
            return names.mapIndexed { index, name ->
                LocationType(
                    id = index + 1,
                    jsonName = name,
                    formattedName = formatStringForUI(name),
                    isChecked = false
                )
            }
        }

        fun formatListForUi(list: List<LocationType>): List<LocationType> {
            return list.map { it.copy(formattedName = formatStringForUI(it.jsonName)) }
        }


        private fun formatStringForUI(input: String): String {
            val words = input.split("_").map { it.capitalize() }

            return words.joinToString(" ")
        }
    }
}