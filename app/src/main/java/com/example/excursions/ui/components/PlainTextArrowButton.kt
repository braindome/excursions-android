package com.example.excursions.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun PlainTextArrowButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .clip(shape = RectangleShape)
            //.width(342.dp)
            .height(22.dp),
        shape = CutCornerShape(0.dp),
        contentPadding = PaddingValues(start = 1.dp, end = 3.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                //.padding(start = 10.dp)

        ) {
            Text(
                text = label,
                fontFamily = polestarFontFamily,
                fontSize = 16.sp,
                color = Color.Black,
                //modifier = Modifier.padding(.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = null, tint = OrangePolestar)

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PlainTextArrowButtonPreview() {
    PlainTextArrowButton(label = "Read more") {

    }
}