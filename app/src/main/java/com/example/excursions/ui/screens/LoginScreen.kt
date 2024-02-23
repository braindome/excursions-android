package com.example.excursions.ui.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.components.ExcursionsButton
import com.example.excursions.ui.components.ExcursionsTextField
import com.example.excursions.ui.theme.ExcursionsTheme
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen() {

    Scaffold(
        modifier = Modifier.padding(20.dp)
    ) { PaddingValues(5.dp)
        Column(
            verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
            Spacer(modifier = Modifier.padding(top = 150.dp))
            Text(
                text = "Welcome to Excursions",
                fontFamily = polestarFontFamily,
                fontSize = 32.sp,
                modifier = Modifier
                    .width(342.dp)
            )
            //Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Log In",
                fontFamily = polestarFontFamily,
                fontSize = 32.sp,
                style = TextStyle(color = GrayPolestar),
                modifier = Modifier
                    .width(342.dp)

            )
            ExcursionsTextField(label = "Email", input = "input")
            ExcursionsTextField(label = "Password", input = "input" )
            Spacer(modifier = Modifier.padding(20.dp))
            ExcursionsButton(label = "Log in")
        }
    }





    /*
    Column(modifier = Modifier.padding(5.dp)) {
        ExcursionsTextField(label = "Email", input = "input")
        ExcursionsTextField(label = "Password", input = "input" )
        Spacer(modifier = Modifier.padding(20.dp))
        ExcursionsButton(label = "Log in")
    }

     */




}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}