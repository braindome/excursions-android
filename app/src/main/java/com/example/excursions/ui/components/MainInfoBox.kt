package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excursions.R
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.calculateRatingAverage
import com.example.excursions.data.model.formatTypesToTags
import com.example.excursions.data.repository.SearchProfileRepository

@Composable
fun MainInfoBox(place: PlaceState) {
    val placeId = place.id

    Column {
        Text(text = "${place.calculateRatingAverage()}/5 Stars!")
        //Text(text = stringResource(id = R.string.lorem_ipsum))
        Spacer(modifier = Modifier.size(10.dp))
        //Text(text = "Primary type: ${place.primaryType}",)
        //Text(text = "Tags: ${place.types.formatTypesToTags()}",)
        PlainTextArrowButton(
            label = "Read more",
            onClick = { /* TODO */ },
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainInfoBoxPreview() {
    MainInfoBox(SearchProfileRepository.dummyPlaceA)
}