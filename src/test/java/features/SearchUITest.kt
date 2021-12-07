package features

import base.BaseUITest
import io.appium.java_client.MobileBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.testng.Assert
import org.testng.annotations.Test

class SearchUITest : BaseUITest() {


    private val searchKey = "alvinati"
    private val hint = "Search for name.."

    @Test
    fun before_search_display() {
        uiWait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("main.label_title")))
        val title = testDriver.findElementByAccessibilityId("main.label_title")
        Assert.assertEquals(title.isDisplayed, true)
        Assert.assertEquals(title.text, "LIST GITHUB USER")

        val searchInput = testDriver.findElementByAccessibilityId("main.input_search")
        Assert.assertEquals(searchInput.isDisplayed, true)
        Assert.assertEquals(searchInput.text, hint)
    }

    @Test
    fun input_search_display() {
        val searchInput = testDriver.findElementByAccessibilityId("main.input_search")

        searchInput.sendKeys(searchKey)
        Assert.assertEquals(searchInput.text, searchKey)

        val buttonClear = testDriver.findElementByAccessibilityId("main.btn_clear_search")
        Assert.assertEquals(buttonClear.isDisplayed, true)
        Assert.assertEquals(buttonClear.isEnabled, true)

        buttonClear.click()
        Assert.assertEquals(searchInput.text, hint)
    }

}