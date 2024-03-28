package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.api_models.Center
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.theme.YellowPolestar
import com.example.excursions.ui.theme.polestarFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun GridCard(
    navController: NavHostController,
    searchProfile: SearchProfile,
    viewModel: ExcursionsViewModel,
    isEditModeOn: Boolean
) {
    val searchProfileId = searchProfile.id
    val currentLocation by viewModel.location.observeAsState()
    val nullCheckedLocation: Center = currentLocation ?: Center(0.00,0.00)


    val placeList by rememberUpdatedState(viewModel.resultPlaceList)

    Surface(
        modifier = Modifier
            .width(173.dp)
            .height(200.dp)
            .padding(4.dp),
        color = YellowPolestar,
        onClick = {
            if (!isEditModeOn) {
                val types = searchProfile.types
                    .filter { it.isChecked }
                    .map { it.jsonName }
                Timber.d("Types into api request: $types")

                viewModel.viewModelScope.launch {
                    // API call disabled for testing
                    viewModel.searchPlacesByLocationAndRadius(center = nullCheckedLocation, types = types, range = searchProfile.range, placeListId = searchProfileId)
                    delay(300)
                    val placeListId = placeList.value.id
                    navController.navigate("swipeScreen/${placeListId}/${searchProfileId}")
                }
            } else {
                navController.navigate("${ExcursionsRoutes.EditSearchProfile.route}/${searchProfileId}")

            }


        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = searchProfile.title,
                fontSize = 22.sp,
                fontFamily = polestarFontFamily,
                modifier = Modifier.padding(2.dp)
            )
            Icon(
                painter = painterResource(id = if (isEditModeOn) R.drawable.plus_large else R.drawable.arrow_top_right),
                contentDescription = null,
                modifier = Modifier.align(Alignment.End).padding(8.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GridCardPreview() {
    GridCard(
        navController = rememberNavController(),
        searchProfile = SearchProfile(id = -1),
        viewModel = ExcursionsViewModel(api = DummyExcursionsAPI(), appContext = LocalContext.current),
        isEditModeOn = false
    )
}

