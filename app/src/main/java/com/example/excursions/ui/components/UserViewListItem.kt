package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.excursions.R
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.PolestarTypography
import com.example.excursions.ui.theme.Typography

@Composable
fun UserViewListItem(label: String, details: String) {

    Column(
        modifier = Modifier.size(height = 56.dp, width = 342.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = PolestarTypography.labelSmall, modifier = Modifier.weight(1f))
            Text(text = details, style = PolestarTypography.labelSmall, modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                tint = OrangePolestar,
                modifier = Modifier.weight(0.5f)
            )
        }
        HorizontalDivider(
            modifier = Modifier,
            thickness = 1.dp,
            color = GrayPolestar
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserViewListItemPreview() {
    UserViewListItem(label = "Log out from account", details = "leopoldo@mastelloni.it")
}