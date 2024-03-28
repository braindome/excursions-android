package com.example.excursions

import android.app.Application
import com.example.excursions.api.ExcursionsAPI
import com.google.android.libraries.places.api.Places
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ExcursionsApp : Application() {
    private val placesBaseUrl = "https://places.googleapis.com/v1/"

    val api:ExcursionsAPI by lazy {
        try {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-Goog-Api-Key", BuildConfig.API_KEY)
                        .addHeader("X-Goog-FieldMask", "places.formattedAddress,places.displayName,places.id,places.location,places.primaryType,places.types,places.reviews,places.photos")
                        .build()
                    chain.proceed(request)
                }
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(placesBaseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(httpClient)
                .build()

            retrofit.create(ExcursionsAPI::class.java)
        } catch (e: Exception) {
            Timber.e(e, "Error during Retrofit and Moshi initialization")
            throw e
        }
    }
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