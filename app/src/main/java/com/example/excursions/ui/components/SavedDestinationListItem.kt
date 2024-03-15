package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.api_models.DisplayName
import com.example.excursions.data.model.PlaceState
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SavedDestinationListItem(
    isEditModeOn: Boolean,
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    onDeleteClicked: () -> Unit,
    searchProfile: SearchProfile,
    place: PlaceState,
    distance: Int
) {
    val placeId = place.id
    Column(
        modifier = Modifier
            .width(342.dp)
            .height(72.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = place.displayName.text,
                    style = TextStyle(
                        fontSize = 26.sp,
                        lineHeight = 26.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = polestarFontFamily,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.6f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$distance km",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.1.sp,
                        fontFamily = polestarFontFamily,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.45f)
                    )
                )
            }

            if (isEditModeOn) {
                IconButton(
                    onClick = {
                        viewModel.removeDestinationFromFavorites(place, searchProfile)
                        onDeleteClicked()
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.cancel
                        ),
                        contentDescription = null,
                        tint = OrangePolestar
                    )
                }
            } else {
                IconButton(onClick = { navController.navigate("${ExcursionsRoutes.PlaceDetailScreen.route}/${placeId}") }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.arrow_right
                        ),
                        contentDescription = null,
                        tint = OrangePolestar
                    )
                }
            }

        }
        HorizontalDivider(thickness = 1.dp, color = Color.Black.copy(alpha = 0.3f))
    }

}



@Preview(showBackground = true)
@Composable
fun SavedDestinationListItemPreview() {
    SavedDestinationListItem(
        isEditModeOn = false,
        viewModel = ExcursionsViewModel(LocalContext.current, DummyExcursionsAPI()),
        distance = 666,
        onDeleteClicked = {},
        place = PlaceState(DisplayName("", "Place name")),
        searchProfile = SearchProfile(""),
        navController = rememberNavController()

    )
}
