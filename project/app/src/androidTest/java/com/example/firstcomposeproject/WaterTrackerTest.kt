package com.example.firstcomposeproject

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class WaterTrackerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun clickAddWater(times: Int) {
        repeat(times) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }
    }

    // 1. Кнопка добавления жидкости есть и работает
    @Test
    fun addWaterButton_isShown_andAddsWater() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("100 мл").assertIsDisplayed()
        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("350 мл").assertIsDisplayed()
    }

    // 2. Кнопка «Завершить день» есть и сбрасывает счётчик воды
    @Test
    fun finishDayButton_isShown_andResetsWater() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("Завершить день").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    // 3. Текст с количеством дней подряд выводится и меняется корректно
    @Test
    fun daysCounter_textIsShown_andUpdatesCorrectly() {
        composeTestRule.setContent { WaterTracker() }

        // День 1: 100 + 6 * 250 = 1600 мл, норма выполнена
        clickAddWater(6)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд с 1500 мл и больше: 1").assertIsDisplayed()

        // День 2: 0 + 6 * 250 = 1500 мл, норма выполнена
        clickAddWater(6)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд с 1500 мл и больше: 2").assertIsDisplayed()

        // День 3: 0 мл, норма не выполнена, счётчик сбрасывается
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд с 1500 мл и больше: 0").assertIsDisplayed()
    }
}