package com.example.excursions.auto;

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.car.app.CarAppService
import androidx.car.app.Screen
import androidx.car.app.ScreenManager
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator


class ExcursionsCarAppService : CarAppService() {
        override fun createHostValidator(): HostValidator {
                return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        }

        override fun onCreateSession(): Session {
                return ExcursionsSession()
        }

        override fun onCreate() {
                super.onCreate()
        }

}

class ExcursionsSession : Session() {
        override fun onCreateScreen(intent: Intent): Screen {
                return if (hasLocationPermission())
                        MainScreen(carContext)
                else {
                        carContext.getCarService(ScreenManager::class.java)
                                .push(MainScreen(carContext))
                        LocationPermissionScreen(carContext)
                }
        }

        private fun hasLocationPermission() =
                (carContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)

}
