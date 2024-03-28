package com.example.excursions.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.polestarFontFamily


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ExcursionsFilterChip(
    label: String,
    isSelected: Boolean,
    onChipClicked: () -> Unit
) {
    var selected by rememberSaveable { mutableStateOf(isSelected) }

    Box(
        modifier = Modifier.wrapContentSize()
    ) {
        FilterChip(
            selected = selected,
            onClick = {
                selected = !selected
                onChipClicked()
            },
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        text = label,
                        fontFamily = polestarFontFamily,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 18.sp,
                            letterSpacing = (-1).sp
                        ),
                        color = if (selected) Color.White else Color.Black
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    Icon(
                        painter = painterResource(id = if (selected) R.drawable.cancel else R.drawable.plus_large),
                        contentDescription = null,
                        tint = OrangePolestar,
                        modifier = Modifier.size(16.dp)
                    )
                }
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color.White,
                selectedContainerColor = Color.Black,
                selectedLabelColor = Color.White
            ),
            shape = RectangleShape,
            modifier = Modifier.height(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExcursionsFilterChipPreview() {
    ExcursionsFilterChip(label = "Typegoeshere", isSelected = false, onChipClicked = {})
}