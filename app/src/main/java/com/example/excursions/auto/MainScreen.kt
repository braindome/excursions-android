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
import com.example.excursions.data.model.SearchProfile
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainScreen(carContext: CarContext) : Screen(carContext) {


    val db = Firebase.firestore
    var profiles = mutableListOf<SearchProfile>()
    init {
        Timber.d("init main screen")
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val searchProfileIds = listOf(1,2,3,4,5,6)
                db.collection("antonio")
                    .get()
                    .addOnSuccessListener { documents ->
                        profiles = documents.toObjects<SearchProfile>().toMutableList()
                        Timber.d("List of profiles: $profiles")
                    }
                    .addOnFailureListener {
                        Timber.d("get failed")
                    }
            } catch (e: Exception) {
                Timber.e("Exception: ${e.cause}")
            }
        }
    }

    override fun onGetTemplate(): Template {

        val gridItemListBuilder = ItemList.Builder()

        profiles.forEach { data ->
            val gridItemBuilder = GridItem.Builder()
                .setTitle(data.title)
                .setOnClickListener { /* TODO */ }
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
}
