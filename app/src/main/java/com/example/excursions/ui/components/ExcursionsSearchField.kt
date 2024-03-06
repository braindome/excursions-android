package com.example.excursions.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily
import com.example.excursions.R


@Composable
fun ExcursionsSearchField(label: String, input: String, modifier: Modifier) {
    var inputState by rememberSaveable {
        mutableStateOf(input)
    }

    Column(
        modifier = modifier.size(height = 75.dp, width = 342.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontFamily = polestarFontFamily,
            lineHeight = 18.sp,
            fontSize = 16.sp
        )
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            TextField(
                value = inputState,
                onValueChange = { inputState = it },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = polestarFontFamily,
                ),
                leadingIcon = {
                    Box(modifier = Modifier.clickable { /* TODO: Perform search */ }) {
                        Icon(painter = painterResource(R.drawable.search), contentDescription = null)
                    }
                },
                trailingIcon = {
                    Box(modifier = Modifier.clickable { inputState = "" }) {// Clears the text field
                        Icon(painter = painterResource(R.drawable.cancel), contentDescription = null)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GrayPolestar,
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.08f),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,

                    ),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                //.padding(0.dp)
            )
        }

        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ExcursionsSearchFieldPreview() {
    ExcursionsSearchField(label = "Search", input = "input", modifier = Modifier)
}