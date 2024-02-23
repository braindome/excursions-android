package com.example.excursions

import android.app.Application
import com.google.android.libraries.places.api.Places
import timber.log.Timber

class ExcursionsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Define a variable to hold the Places API key.
        val apiKey = BuildConfig.PLACES_API_KEY
        if (apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY") {
            Timber.e("Places test", "No api key")
            //finish()
            return
        }


        // Initialize the SDK
        Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)


    }
}