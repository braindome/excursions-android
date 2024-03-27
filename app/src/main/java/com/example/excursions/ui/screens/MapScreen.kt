package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.api_models.Location
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

@Composable
fun MapScreen(
    placeId: String,
    viewModel: ExcursionsViewModel,
    navController: NavHostController
) {
    val uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val placeState = viewModel.getPlaceById(placeId)

    val cameraPositionState = CameraPositionState(
        position = CameraPosition(
            LatLng(
                placeState.location.latitude,
                placeState.location.longitude),
            8f, 0f, 0f
        )
    )

    val markerState by remember {
        mutableStateOf(
            MarkerState(LatLng(
                placeState.location.latitude,
                placeState.location.longitude
            ))
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = markerState,
                title = placeState.displayName.text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(
        placeId = "asdas",
        navController = rememberNavController(),
        viewModel = ExcursionsViewModel(LocalContext.current, api = DummyExcursionsAPI())
    )
}