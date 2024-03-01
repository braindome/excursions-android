package com.example.excursions.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsRoutes
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.components.PlainTextArrowButton
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun IntroScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.padding(top = 178.dp))
        ScreenTitleSubtitle(
            title = "Welcome to Excursions",
            subtitle = "Log in",
            modifier = Modifier.weight(0.5f).padding(top = 200.dp)
        )
        //Spacer(modifier = Modifier.padding(top = 256.dp))
        Text(
            text = "Continue below or create account to access the excursions app.",
            fontFamily = polestarFontFamily,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp
            ),
            modifier = Modifier
                .width(342.dp)
                .weight(0.05f)
        )
        //Spacer(modifier = Modifier.padding(top = 8.dp))
        //ExcursionsButton(label = "Log in") { navController.navigate("login") })
        ExcursionsButton(
            label = "Log in",
            onClick = { navController.navigate(ExcursionsRoutes.Login.route) },
            modifier = Modifier//.weight(0.05f)
        )
        //Spacer(modifier = Modifier.padding(top = 16.dp))
        PlainTextArrowButton(
            label = "Create Account",
            onClick = { navController.navigate("login") /* TODO */ },
            modifier = Modifier.weight(0.05f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(rememberNavController())
}
