package com.example.excursions.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.WhitePolestar
import com.example.excursions.ui.theme.polestarFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcursionsTextField(label: String, input: String) {
    val unfocusedColor = GrayPolestar
    var inputState by rememberSaveable { mutableStateOf(input) }
    Column(
        modifier = Modifier
            .width(342.dp)
    ) {
        Text(
            text = label,
            fontFamily = polestarFontFamily,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Box(
            modifier = Modifier
                .background(
                    color = GrayPolestar
                )
                .clipToBounds()

        ) {

            /*
            TextField(
                value = inputState,
                onValueChange = { inputState = it },
                textStyle = TextStyle(fontSize = 16.sp, fontFamily = polestarFontFamily),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .height(48.dp),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = GrayPolestar
                )

            )

             */

            val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = inputState,
                onValueChange = { inputState = it },
                textStyle = TextStyle(fontSize = 16.sp, fontFamily = polestarFontFamily),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                interactionSource = interactionSource,
                singleLine = true,
            ) { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = inputState,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(start = 3.dp), // this is how you can remove the padding,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = GrayPolestar,
                        focusedContainerColor = WhitePolestar
                    )
                )
            }
            Spacer(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExcursionsTextFieldPreview() {
    ExcursionsTextField(label = "Email", input = "input")
}