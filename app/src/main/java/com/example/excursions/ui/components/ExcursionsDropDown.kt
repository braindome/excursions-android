package com.example.excursions.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excursions.R
import com.example.excursions.ui.theme.GrayPolestar
import com.example.excursions.ui.theme.polestarFontFamily

@Composable
fun ExcursionsDropDown(label: String) {
    var expanded by remember { mutableStateOf(false) }
    val mockList = listOf("A", "B", "C", "D", "E")
    val disabledValue = "B"
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .height(75.dp)
            .width(342.dp)
    ) {
        Text(
            text = label,
            fontFamily = polestarFontFamily,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp
            )
        )
        Box(modifier = Modifier
            .width(342.dp)
            .height(48.dp)
            //.wrapContentSize(Alignment.TopStart)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { expanded = true } )
                    .background(GrayPolestar)
                    .padding(10.dp)
                    //.width(342.dp)
            ) {
                Text(
                    text = mockList[selectedIndex],
                    fontFamily = polestarFontFamily,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 18.sp
                    ),
                    modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                        //.fillMaxSize()
                        //.clickable(onClick = { expanded = true })
                        //.background(GrayPolestar)
                        //.padding(10.dp)
                )
                Icon(
                    painter = painterResource(id = if (expanded) R.drawable.hook_up_16 else R.drawable.hook_down_16),
                    //painter = painterResource(id = R.drawable.hook_down_16),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    //.fillMaxWidth()
                    .background(
                        Color.White
                    )
            ) {
                mockList.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = {
                            val disabledText = if (s == disabledValue) {
                                " (Disabled)"
                            } else {
                                ""
                            }
                            Text(text = s + disabledText)
                        },
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        }
                    )
                }
            }
        }
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
                //.fillMaxWidth()
                //.padding(vertical = 8.dp) // Adjust padding as needed
        )
    }


}

@Preview(showBackground = true)
@Composable
fun ExcursionsDropDownPreview() {
    ExcursionsDropDown("Label")
}