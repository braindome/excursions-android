package com.example.excursions.data.repository

import androidx.compose.ui.text.capitalize
import com.example.excursions.data.api_models.AuthorAttribution
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
import com.example.excursions.data.api_models.OriginalText
import com.example.excursions.data.api_models.Review
import com.example.excursions.data.api_models.Text
import com.example.excursions.data.model.LocationType
import com.example.excursions.data.model.PlaceList
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.SearchProfile
import java.util.InputMismatchException

class SearchProfileRepository {



    companion object {
        val outdoorAdventure : MutableList<LocationType> = createLocationTypes(
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

        val culturalExploration : MutableList<LocationType> = createLocationTypes(
            "art_gallery",
            "museum",
            "performing_arts_theater",
            "cultural_center",
            "historical_landmark",
            "library",
            "university"
        )

        val landmarkDiscovery : MutableList<LocationType> = createLocationTypes(
            "historical_landmark",
            "museum",
            "tourist_attraction",
            "national_park",
            "visitor_center",
            "city_hall",
            "embassy"
        )

        val relaxationAndWellness : MutableList<LocationType> = createLocationTypes(
            "spa",
            "resort_hotel",
            "bed_and_breakfast",
            "private_guest_room",
            "extended_stay_hotel"
        )

        val entertainmentHub : MutableList<LocationType> = createLocationTypes(
            "amusement_park",
            "casino",
            "night_club",
            "movie_theater",
            "sports_club",
            "bowling_alley"
        )

        val carServices : MutableList<LocationType> = createLocationTypes(
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

        val dummyPlaceA = PlaceState(
            displayName = DisplayName(
                languageCode = "EN",
                text = "Gothenburg Botanical Garden"
            ),
            formattedAddress = "Carl Skottsbergs gata 22A, 413 19 Göteborg, Sweden",
            id = "ChIJNdXcPRrzT0YRr8L_0fbf7wQ",
            location = Location(
                latitude = 57.6828534,
                longitude = 11.950377,
            ),
            primaryType = "park",
            types = listOf("tourist_attraction", "hiking_area", "park", "point_of_interest", "establishment"),
            reviews = listOf(
                Review(
                    authorAttribution = AuthorAttribution(
                        displayName = "Charity Essien",
                        photoUri = "none",
                        uri = "none"
                    ),
                    name = "asdsf",
                    originalText = OriginalText(
                        languageCode = "EN",
                        text = "asdadsasda"
                    ),
                    publishTime = "1223",
                    relativePublishTimeDescription = "5 months ago",
                    rating = 5,
                    text = Text(
                        languageCode = "EN",
                        text = "Very beautiful garden \uD83E\uDEB4 with a lot of places in it to explore. Enjoyed most with family and friends."
                    ),
                ),
                Review(
                    authorAttribution = AuthorAttribution(
                        displayName = "Arsalan",
                        photoUri = "none",
                        uri = "none"
                    ),
                    name = "asdsf",
                    originalText = OriginalText(
                        languageCode = "EN",
                        text = "asdadsasda"
                    ),
                    publishTime = "1223",
                    relativePublishTimeDescription = "6 days ago",
                    rating = 5,
                    text = Text(
                        languageCode = "EN",
                        text = "Free entry, walked right in, not too busy, perfect weather even though we expected rain, some really lovely areas to walk around.  Perfect end to our trip on the way to the airport home."
                    ),
                ),
                Review(
                    authorAttribution = AuthorAttribution(
                        displayName = "Naz Simsek",
                        photoUri = "none",
                        uri = "none"
                    ),
                    name = "asdsf",
                    originalText = OriginalText(
                        languageCode = "EN",
                        text = "asdadsasda"
                    ),
                    publishTime = "1223",
                    relativePublishTimeDescription = "5 months ago",
                    rating = 5,
                    text = Text(
                        languageCode = "EN",
                        text = "\"Disappointing Julbord experience unfortunately.\\n\\nUnorganised reception, mediocre menu for the price & offensive serving for wine package.\\n\\nThe food was fine, edible but forgettable. 695kr per person gets you\\n\\nRaw Carrot Salad & pumpkin purée\\n(cold dish)\\n\\nEgg cream pickled vegetables & smoked fish\\n(Cold dish)\\n\\nWild Board cabbage lentils tomatoes white sauce\\n(Warm dish)\\n\\nLike I said, food was ok but the serving portions for the 395kr wine package was upsetting.\\n\\n395kr gets you 3 glasses of lovely natural wines.\\n\\nIt’s just such a shame a serving is not even half a glass.\\n\\nThe 3 “glasses” served would make up 1 glass really.\\n\\nBeautiful surroundings, cosy restaurant, disappointing experience for Christmas.\\n\\n5/10\""
                    ),
                ),
            )
        )

        val dummyPlaceB = PlaceState(
            displayName = DisplayName(
                languageCode = "EN",
                text = "Gunnebo Palace and Gardens"
            ),
            formattedAddress = "Christina Halls väg, 431 36 Mölndal, Sweden",
            id = "ChIJvdJmzILxT0YR9HktQyczUxk",
            location = Location(
                latitude = 57.65797,
                longitude = 12.05835
            ),
            primaryType = "restaurant",
            types = listOf(
                "restaurant",
                "wedding_venue",
                "museum",
                "bakery",
                "event_venue",
                "hiking_area",
                "park",
                "historical_landmark",
                "store",
                "food",
                "point_of_interest",
                "establishment"),
            reviews = listOf(
                Review(
                    authorAttribution = AuthorAttribution(
                        displayName = "Charity Essien",
                        photoUri = "none",
                        uri = "none"
                    ),
                    name = "asdsf",
                    originalText = OriginalText(
                        languageCode = "EN",
                        text = "asdadsasda"
                    ),
                    publishTime = "1223",
                    relativePublishTimeDescription = "5 months ago",
                    rating = 5,
                    text = Text(
                        languageCode = "EN",
                        text = "Very beautiful garden \uD83E\uDEB4 with a lot of places in it to explore. Enjoyed most with family and friends."
                    ),
                ),
                Review(
                    authorAttribution = AuthorAttribution(
                        displayName = "Arsalan",
                        photoUri = "none",
                        uri = "none"
                    ),
                    name = "asdsf",
                    originalText = OriginalText(
                        languageCode = "EN",
                        text = "asdadsasda"
                    ),
                    publishTime = "1223",
                    relativePublishTimeDescription = "6 days ago",
                    rating = 5,
                    text = Text(
                        languageCode = "EN",
                        text = "Free entry, walked right in, not too busy, perfect weather even though we expected rain, some really lovely areas to walk around.  Perfect end to our trip on the way to the airport home."
                    ),
                ),
                Review(
                    authorAttribution = AuthorAttribution(
                        displayName = "Naz Simsek",
                        photoUri = "none",
                        uri = "none"
                    ),
                    name = "asdsf",
                    originalText = OriginalText(
                        languageCode = "EN",
                        text = "asdadsasda"
                    ),
                    publishTime = "1223",
                    relativePublishTimeDescription = "5 months ago",
                    rating = 5,
                    text = Text(
                        languageCode = "EN",
                        text = "\"Disappointing Julbord experience unfortunately.\\n\\nUnorganised reception, mediocre menu for the price & offensive serving for wine package.\\n\\nThe food was fine, edible but forgettable. 695kr per person gets you\\n\\nRaw Carrot Salad & pumpkin purée\\n(cold dish)\\n\\nEgg cream pickled vegetables & smoked fish\\n(Cold dish)\\n\\nWild Board cabbage lentils tomatoes white sauce\\n(Warm dish)\\n\\nLike I said, food was ok but the serving portions for the 395kr wine package was upsetting.\\n\\n395kr gets you 3 glasses of lovely natural wines.\\n\\nIt’s just such a shame a serving is not even half a glass.\\n\\nThe 3 “glasses” served would make up 1 glass really.\\n\\nBeautiful surroundings, cosy restaurant, disappointing experience for Christmas.\\n\\n5/10\""
                    ),
                ),
            )
        )

        val dummyPlaceList = PlaceList(list = mutableListOf(dummyPlaceA, dummyPlaceB))

        private fun createLocationTypes(vararg names: String): MutableList<LocationType> {
            return names.mapIndexed { index, name ->
                LocationType(
                    id = index + 1,
                    jsonName = name,
                    formattedName = formatStringForUI(name),
                    isChecked = true
                )
            }.toMutableList()
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