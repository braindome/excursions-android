package com.example.excursions.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.components.PlainTextArrowButton
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.navigation.ExcursionsRoutes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
        //Spacer(modifier = Modifier.padding(top = 178.dp))
        ScreenTitleSubtitle(
            title = "Welcome to Excursions",
            subtitle = "Log in",
            modifier = Modifier.weight(0.2f).padding(top = 200.dp)
        )
        //Spacer(modifier = Modifier.padding(32.dp))
        ExcursionsTextField(label = "Email", input = "input", modifier = Modifier.weight(0.05f).padding(vertical = 8.dp), onInputChanged = {})
        ExcursionsTextField(label = "Password", input = "input", modifier = Modifier.weight(0.05f).padding(vertical = 8.dp), onInputChanged = {} )
        Spacer(modifier = Modifier.padding(40.dp))
        ExcursionsButton(
            label = "Log in",
            onClick =  { navController.navigate(ExcursionsRoutes.Categories.route) },
            modifier = Modifier.padding(vertical = 4.dp)
        )
        //Spacer(modifier = Modifier.padding(8.dp))
        PlainTextArrowButton(label = "Forgot password?", onClick = {}, modifier = Modifier)
        //Spacer(modifier = Modifier.padding(0.dp))
        PlainTextArrowButton(label = "Privacy", onClick = {}, modifier = Modifier)


    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}