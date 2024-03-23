package com.example.excursions.auto

import android.content.Intent
import android.net.Uri
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import com.example.excursions.R
import com.example.excursions.data.model.PlaceState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber

class FavoriteListScreen(
    carContext: CarContext,
    searchProfileId: Int
) : Screen(carContext) {
    val db = Firebase.firestore
    var places = mutableListOf<PlaceState>()
    private var placeListLimit = 6

    init {
        fetchPlaces(searchProfileId)
    }

    override fun onGetTemplate(): Template {

        return if (places.isEmpty()) {
            MessageTemplate.Builder("Loading...")
                .setHeaderAction(Action.BACK)
                .build()
        } else {
            val listBuilder = ItemList.Builder()

            places.take(placeListLimit).forEachIndexed { index, placeState ->
                Timber.d("PlaceState in auto list: ${placeState.displayName.text}")
                if (placeState.isFavorite) {
                    listBuilder
                        .addItem(
                            Row.Builder().setOnClickListener {
                                openGoogleMaps(placeState)
                            }
                                .setImage(
                                    CarIcon.Builder(
                                        IconCompat.createWithResource(
                                            carContext,
                                            R.drawable.arrow_right
                                        )
                                    ).build()
                                )
                                .setTitle(placeState.displayName.text)

                                .build()
                        )
                } else return@forEachIndexed

            }

            return ListTemplate.Builder()
                .setTitle("Saved Destinations")
                .setHeaderAction(Action.BACK)
                .apply { setSingleList(listBuilder.build()) }
                .build()
        }


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchPlaces(searchProfileId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val querySapshot = withContext(Dispatchers.IO) {
                    db.collection("antonio")
                        .document(searchProfileId.toString())
                        .collection("savedDestinations")
                        .limit(placeListLimit.toLong())
                        .get().await()
                }
                val retrievedPlaces = mutableListOf<PlaceState>()
                for (document in querySapshot.documents) {
                    val placeState = document.toObject<PlaceState>()
                    if (placeState != null) {
                        Timber.d("Place retrieved: ${placeState.displayName.text}")
                    }
                    placeState?.let { retrievedPlaces.add(it) }
                }

                places = retrievedPlaces
                invalidate()
            } catch (e: Exception) {
                Timber.e("Error fetching places from Firestore: ${e.cause}")
            }
        }
    }

    private fun openGoogleMaps(placeState: PlaceState) {
        val gmmIntentUri: Uri = Uri.parse("geo:${placeState.location.latitude},${placeState.location.longitude}?q=${Uri.encode(placeState.displayName.text)}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (mapIntent.resolveActivity(carContext.packageManager) != null) {
            carContext.startActivity(mapIntent)
        } else {
            Timber.e("Google Maps app not installed")
        }
    }


}