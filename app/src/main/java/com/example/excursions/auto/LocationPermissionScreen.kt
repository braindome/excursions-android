package com.example.excursions.auto

import android.Manifest
import android.annotation.SuppressLint
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarColor
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.ParkedOnlyOnClickListener
import androidx.car.app.model.Template

class LocationPermissionScreen(carContext: CarContext) : Screen(carContext) {
    @SuppressLint("UnsafeOptInUsageError")
    override fun onGetTemplate(): Template {
        val permissions: MutableList<String> = ArrayList()
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        val grantAction = Action.Builder()
            .setTitle("Accept")
            .setFlags(Action.FLAG_PRIMARY)
            .setOnClickListener(
                ParkedOnlyOnClickListener.create {
                    carContext.requestPermissions(permissions) { _, _ ->
                        finish()
                    }
                }
            )
            .build()

        val skipAction = Action.Builder()
            .setTitle("Decline")
            .setBackgroundColor(CarColor.SECONDARY)
            .setOnClickListener { finish() }
            .build()

        return MessageTemplate
            .Builder("Share your position?")
            .addAction(grantAction)
            .addAction(skipAction)
            .setTitle("Excursions")
            .setHeaderAction(Action.APP_ICON)
            .build()
    }
}
