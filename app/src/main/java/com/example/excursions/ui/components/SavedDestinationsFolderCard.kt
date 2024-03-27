package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.excursions.ExcursionsViewModel
import com.example.excursions.R
import com.example.excursions.data.model.SearchProfile
import com.example.excursions.data.repository.DummyExcursionsAPI
import com.example.excursions.ui.navigation.ExcursionsRoutes
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SavedDestinationsFolderCard(
    navController: NavHostController,
    viewModel: ExcursionsViewModel,
    searchProfile: SearchProfile
) {
    val searchProfileId = searchProfile.id
    Surface(
        modifier = Modifier
            .width(173.dp)
            .height(200.dp)
            .padding(3.dp),
        color = GrayPolestar,
        onClick = { navController.navigate(ExcursionsRoutes.Saved.route + "/${searchProfileId}") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = searchProfile.title,
                fontSize = 22.sp,
                fontFamily = polestarFontFamily,
                modifier = Modifier.padding(2.dp)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(painter = painterResource(id = R.drawable.arrow_top_right), contentDescription = null )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SavedDestinationsFolderCardPreview() {
    SavedDestinationsFolderCard(
        navController = rememberNavController(),
        searchProfile = SearchProfile(id = -1),
        viewModel = ExcursionsViewModel(
            api = DummyExcursionsAPI(),
            appContext = LocalContext.current
        )
    )
}