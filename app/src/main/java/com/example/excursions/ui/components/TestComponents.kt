package com.example.excursions.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excursions.api.ExcursionsAPI
import com.example.excursions.data.api_models.SearchNearbyRequest
import com.example.excursions.data.api_models.SearchNearbyResponse
import com.example.excursions.ui.theme.GrayPolestar
import retrofit2.Call

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExample(label: String) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text(label)
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = GrayPolestar
        ),
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
        modifier = Modifier.height(32.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FilterChipExamplePreview() {
    FilterChipExample("Filter Chip")
}

// Define a dummy implementation of ExcursionsAPI for the preview
class DummyExcursionsAPI : ExcursionsAPI {
    // Implement required methods with dummy behavior for preview
    // ...
    override fun searchNearbyPlaces(
        url: String,
        request: SearchNearbyRequest
    ): Call<SearchNearbyResponse> {
        TODO("Not yet implemented")
    }

    override fun searchNearbyPlaces__(request: SearchNearbyRequest): Call<SearchNearbyResponse> {
        TODO("Not yet implemented")
    }
}
