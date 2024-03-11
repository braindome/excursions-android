package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.Typography
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun SwipeActionBar(
    onYayClick: () -> Unit,
    onNayClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .width(342.dp)
            .height(36.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onNayClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null, tint = OrangePolestar,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "Nay",
            style = Typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Yay",
            style = Typography.bodyLarge,
            //modifier = Modifier.weight(1f)

        )
        IconButton(onClick = { onYayClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null, tint = OrangePolestar,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwipeActionBarPreview() {
    SwipeActionBar(
        {}, {}
    )
}