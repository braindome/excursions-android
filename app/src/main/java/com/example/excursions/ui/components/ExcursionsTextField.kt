package com.example.excursions.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.WhitePolestar
import com.example.excursions.ui.theme.polestarFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcursionsTextField(label: String, input: String, modifier: Modifier) {
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
            onValueChange = { inputState = it },
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = polestarFontFamily,
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrayPolestar,
                unfocusedContainerColor = GrayPolestar,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Black,

            ),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.fillMaxWidth().padding(0.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ExcursionsTextFieldPreview() {
    ExcursionsTextField(label = "Email", input = "input", modifier = Modifier)
}