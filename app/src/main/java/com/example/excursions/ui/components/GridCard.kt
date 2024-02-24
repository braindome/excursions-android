package com.example.excursions.ui.components

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.YellowPolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun GridCard(title: String, navController: NavHostController) {
    var categoryNameState by rememberSaveable { mutableStateOf(title) }
    Surface(
        modifier = Modifier
            .width(173.dp)
            .height(206.dp)
            .padding(3.dp),
        color = YellowPolestar,
        onClick = { navController.navigate("swipeScreen") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = categoryNameState,
                fontSize = 22.sp,
                fontFamily = polestarFontFamily,
                modifier = Modifier.padding(2.dp)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(painter = painterResource(id = R.drawable.arrow_top_right), contentDescription = null )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GridCardPreview() {
    GridCard("Category name", rememberNavController())
}