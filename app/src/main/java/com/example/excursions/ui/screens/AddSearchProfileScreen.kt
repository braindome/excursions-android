package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ui.components.BottomNavBar
import com.example.excursions.ui.components.ExcursionsBottomBar
import com.example.excursions.ui.components.ExcursionsDropDown
import com.example.excursions.ui.components.ExcursionsSlider
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.components.ExcursionsTopBar
import com.example.excursions.ui.components.ScreenTitleText

@Composable
fun AddSearchProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ExcursionsTopBar(navController = navController, backDestination = "categories", rightButtonDestination = "", rightButtonLabel = "Add") },
        bottomBar = { ExcursionsBottomBar(navController = navController) }

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {


            ScreenTitleText(title = "Add Search Profile")
            Spacer(modifier = Modifier.size(20.dp))
            ExcursionsTextField(label = "Name", input = "")
            Spacer(modifier = Modifier.size(10.dp))
            ExcursionsDropDown("Type")
            Spacer(modifier = Modifier.size(10.dp))
            ExcursionsSlider()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AddSearchProfilePreview() {
    AddSearchProfileScreen(navController = rememberNavController())
}