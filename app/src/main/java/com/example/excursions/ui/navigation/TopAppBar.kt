package com.example.excursions.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.R
import com.example.excursions.ui.theme.OrangePolestar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcursionsTopBar(
    navController: NavHostController,
    rightButtonDestination: String?,
    rightButtonLabel: String?,
    onEndButtonClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = null,
                    tint = OrangePolestar,
                    modifier = Modifier.size(16.dp)
                )
            }

        },
        actions = {
            Column(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (rightButtonDestination != null) {
                            navController.navigate(rightButtonDestination)
                        }
                        onEndButtonClick?.invoke()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .clip(shape = RectangleShape)
                        .height(22.dp),
                    shape = CutCornerShape(0.dp),
                    contentPadding = PaddingValues(0.dp),
                ) {
                    if (rightButtonLabel != null) {
                        Text(
                            text = rightButtonLabel,
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 18.sp,
                                color = Color.Black
                            ),
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ExcursionsTopBarPreview() {
    ExcursionsTopBar(navController = rememberNavController(), rightButtonDestination = "", rightButtonLabel = "Add")
}