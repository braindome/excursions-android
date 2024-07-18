package com.example.excursions.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.components.ScreenTitleText
import com.example.excursions.ui.components.SwipeActionBar
import com.example.excursions.ui.components.SwipeCard
import com.example.excursions.ui.navigation.ExcursionsBottomBar
import com.example.excursions.ui.navigation.ExcursionsTopBar
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun SwipeScreen(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    placeListId: String,
    searchProfileId: Int
) {

    val swipeList by viewModel.resultPlaceList.collectAsState()
    val searchProfile by viewModel.searchProfile.collectAsState()
    var currentPlaceIndex by rememberSaveable { mutableIntStateOf(0) }
    val title by rememberSaveable { mutableStateOf(viewModel.getSearchProfileById(searchProfileId).title) }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        isLoading = false
    }



    Timber.d("Received placeListId: $placeListId")
    Timber.d("Place list ID: ${swipeList.id}, size: ${swipeList.list.size}")

    Scaffold(
        topBar = {
            ExcursionsTopBar(
                navController = navController,
                rightButtonDestination = "",
                rightButtonLabel = ""
            )},
        bottomBar = { ExcursionsBottomBar(navController = navController) }
    ) { innerPadding ->
        if (isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(color = Color.Black)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenTitleText(title = title)
                Spacer(modifier = Modifier.weight(1f))
                if (swipeList.list.isNotEmpty() && currentPlaceIndex < swipeList.list.size) {
                    SwipeCard(navController = navController, place = swipeList.list[currentPlaceIndex], viewModel = viewModel)
                    Spacer(modifier = Modifier.weight(1f))
                    SwipeActionBar(
                        onYayClick = {
                            //viewModel.saveDestination(searchProfileId, swipeList.list[currentPlaceIndex])
                            viewModel.savePlaceToFirestore(searchProfileId, swipeList.list[currentPlaceIndex])
                            currentPlaceIndex++
                        },
                        onNayClick = {
                            viewModel.discardDestination(searchProfileId, swipeList.list[currentPlaceIndex])
                            currentPlaceIndex++
                        }
                    )
                } else {
                    Text("No places available", modifier = Modifier
                        .padding(16.dp)
                        .weight(1f))
                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SwipeScreenPreview() {
    SwipeScreen(
        navController = rememberNavController(),
        viewModel = ExcursionsViewModel(api = DummyExcursionsAPI(), appContext = LocalContext.current),
        placeListId = "abc",
        searchProfileId = -1
    )
}
