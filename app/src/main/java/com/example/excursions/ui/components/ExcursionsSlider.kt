package com.example.excursions.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.Typography
import com.example.excursions.ui.theme.polestarFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcursionsSlider(
    value: Float,
    onValueChanged: (Float) -> Unit
) {
    var sliderPosition by rememberSaveable { mutableFloatStateOf(value) }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }


    Column(
        modifier = Modifier.size(height = 84.dp, width = 342.dp)
    ) {
        Row(
            modifier = Modifier
                .width(342.dp)
                .weight(0.5f)
        ) {
            Text(
                text = "Range",
                style = Typography.labelSmall
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Km",
                style = Typography.labelSmall
            )
        }
        Slider(
            value = sliderPosition / 1000,
            onValueChange = {
                sliderPosition = it * 1000
                onValueChanged(it * 1000) },
            onValueChangeFinished = {
                onValueChanged(sliderPosition)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Black,
                inactiveTrackColor = GrayPolestar
            ),
            steps = 50,
            valueRange = 0f..50f, // Max range 50km,
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Black
                    ),

                )
            },
            modifier = Modifier
                .size(width = 342.dp, height = 48.dp)
                .weight(1f)

        )
        Text(
            //text = "${(sliderPosition.toInt())/1000} km",
            text = "${(sliderPosition/1000).toInt()} km",
            style = Typography.labelSmall
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ExcursionsSliderPreview() {
    ExcursionsSlider(onValueChanged = {}, value = 0f)
}

