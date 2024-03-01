package com.example.excursions.ui.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.excursions.AppScreen
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.components.PlainTextArrowButton
import com.example.excursions.ui.components.ScreenTitleSubtitle
import com.example.excursions.ui.theme.ExcursionsTheme
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily

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
        ExcursionsTextField(label = "Email", input = "input", modifier = Modifier.weight(0.05f).padding(vertical = 8.dp))
        ExcursionsTextField(label = "Password", input = "input", modifier = Modifier.weight(0.05f).padding(vertical = 8.dp) )
        Spacer(modifier = Modifier.padding(40.dp))
        ExcursionsButton(
            label = "Log in",
            onClick =  { navController.navigate("categories") },
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