package com.example.excursions.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.excursions.R
import com.example.excursions.ui.theme.OrangePolestar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcursionsTopBar() {
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                tint = OrangePolestar
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(15.dp)
    )
}