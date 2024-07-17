package com.example.excursions

import com.example.excursions.ui.components.ExcursionsSlider

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.swipeRight
import org.junit.Rule
import org.junit.Test

class ExcursionsSliderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sliderValueChangesOnSwipe() {
        var sliderValue = 0f
        composeTestRule.setContent {
            ExcursionsSlider(sliderValue) { newValue ->
                sliderValue = newValue
            }
        }

        composeTestRule.onNodeWithText("Range").performGesture { swipeRight() }

        assert(sliderValue > 0f)
    }

    @Test
    fun sliderValueDoesNotExceedMaxValue() {
        var sliderValue = 0f
        composeTestRule.setContent {
            ExcursionsSlider(sliderValue) { newValue ->
                sliderValue = newValue
            }
        }

        for (i in 1..100) {
            composeTestRule.onNodeWithText("Range").performGesture { swipeRight() }
        }

        assert(sliderValue <= 50f)
    }

    @Test
    fun sliderValueDoesNotGoBelowMinValue() {
        var sliderValue = 50f
        composeTestRule.setContent {
            ExcursionsSlider(sliderValue) { newValue ->
                sliderValue = newValue
            }
        }

        for (i in 1..100) {
            composeTestRule.onNodeWithText("Range").performGesture { swipeRight() }
        }

        assert(sliderValue >= 0f)
    }

    @Test
    fun sliderValueChangesOnThumbClick() {
        var sliderValue = 0f
        composeTestRule.setContent {
            ExcursionsSlider(sliderValue) { newValue ->
                sliderValue = newValue
            }
        }

        composeTestRule.onNodeWithText("Range").performClick()

        assert(sliderValue > 0f)
    }
}
