package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily


@Composable
fun ExcursionsTextField(
    label: String,
    input: String,
    onInputChanged: (String) -> Unit,
    modifier: Modifier
) {
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
        TextField(
            value = inputState,
            onValueChange = {
                inputState = it
                onInputChanged(it)
                            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = polestarFontFamily,
            ),
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
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ExcursionsTextFieldPreview() {
    ExcursionsTextField(label = "Email", input = "input", modifier = Modifier, onInputChanged = {})
}