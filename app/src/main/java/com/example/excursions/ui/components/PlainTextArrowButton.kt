package com.example.excursions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.OrangePolestar
import com.example.excursions.ui.theme.PolestarTypography
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun PlainTextArrowButton(label: String, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        modifier = modifier
            //.clip(shape = RectangleShape)
            //.fillMaxWidth(),
            .width(342.dp)
            .height(22.dp),
        shape = CutCornerShape(0.dp),
        contentPadding = PaddingValues(start = 0.dp, end = 0.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth().height(22.dp)
                //.padding(start = 10.dp)

        ) {
            Text(
                text = label,
                style = PolestarTypography.labelSmall,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = null, tint = OrangePolestar)

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PlainTextArrowButtonPreview() {
    PlainTextArrowButton(label = "Read more", modifier = Modifier, onClick = {})
}