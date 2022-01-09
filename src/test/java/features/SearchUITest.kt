package features

import base.BaseUITest
import io.appium.java_client.MobileBy
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import model.Direction
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.support.ui.ExpectedConditions
import org.testng.Assert
import org.testng.annotations.Test
import page.SearchPage
import util.Helper

class SearchUITest : BaseUITest() {


    private val searchKey = "alvinati"
    private val hint = "Search for name.."


    @Test
    fun before_search_display() {
        searchPage.waitToDisplay()
        searchPage.assertInput(hint)
        takeScreenShot("before_search_display")
    }

    @Test
    fun input_search_display() {
        searchPage.waitToDisplay()
        searchPage.inputSearch(searchKey)
        searchPage.assertInput(searchKey)
        takeScreenShot("input_search_display")
    }
}