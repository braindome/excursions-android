package com.example.excursions.auto

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.ItemList
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.lifecycleScope
import com.example.excursions.R
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.SearchProfile
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainScreen(carContext: CarContext) : Screen(carContext) {


    val db = Firebase.firestore
    var profiles = mutableListOf<SearchProfile>()
    init {
        fetchProfiles()
    }

    override fun onGetTemplate(): Template {

        val gridItemListBuilder = ItemList.Builder()

        profiles.forEach { data ->
            Timber.d("Saved Destinations for each profile: ${data.savedDestinations}")
            Timber.d("Profiles: $data")
            val gridItemBuilder = GridItem.Builder()
                .setTitle(data.title)
                .setOnClickListener {
                    screenManager.push(
                        FavoriteListScreen(
                            carContext,
                            data.id
                        )
                    )
                }
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(
                            carContext,
                            R.drawable.arrow_right
                        )
                    ).build()
                ).build()
            gridItemListBuilder.addItem(gridItemBuilder)
        }

        return GridTemplate.Builder()
            .setTitle("Excursions")
            .apply {
                setSingleList(gridItemListBuilder.build())
            }
            .build()


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchProfiles() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val querySapshot = withContext(Dispatchers.IO) {
                    db.collection("antonio")
                        .get().await()
                }
                val retrievedProfiles = mutableListOf<SearchProfile>()
                for (document in querySapshot.documents) {
                    val searchProfile = document.toObject<SearchProfile>()
                    if (searchProfile != null) {
                        Timber.d("Place retrieved: ${searchProfile.title}")
                    }
                    searchProfile?.let { retrievedProfiles.add(it) }
                }

                profiles = retrievedProfiles
                invalidate()
            } catch (e: Exception) {
                Timber.e("Error fetching places from Firestore: ${e.cause}")
            }
        }
    }
}
