package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excursions.R
import com.example.excursions.ui.theme.polestarFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcursionsFilterChip(label: String) {
    var selected by remember { mutableStateOf(false) }

    BoxWithConstraints {
        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = label,
                        fontFamily = polestarFontFamily,
                        color = if (selected) Color.White else Color.Black
                    )

                    Icon(
                        painter = painterResource(id = if (selected) R.drawable.check else R.drawable.plus),
                        contentDescription = null,
                        tint = if (selected) Color.White else Color.Black
                    )
                }
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color.White,
                selectedContainerColor = Color.Black,
                selectedLabelColor = Color.White
            ),
            shape = RectangleShape,
            modifier = Modifier.size(height = 32.dp, width = 156.dp)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun ExcursionsFilterChipPreview() {
    ExcursionsFilterChip(label = "Typesgohere")
}