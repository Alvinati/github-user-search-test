package features

import base.BaseUITest
import config.DeviceType
import config.TestConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.testng.annotations.Test

class SearchUITest : BaseUITest() {


    private val testSSPath = "search-ui-test/"

    private val searchKey = "alvinati"
    private val hint = "Search for name.."

    override fun getDriver(): AppiumDriver<out MobileElement> {
        val caps = TestConfig.androidCapabilities(DeviceType.Device2)
        return AndroidDriver(TestConfig.url, caps)
    }

    @Test
    fun before_search_display() {
        searchPage.waitToDisplay()
        searchPage.assertInput(hint)
        takeScreenShot(testSSPath, "before_search_display")
    }

    @Test
    fun input_search_display() {
        searchPage.waitToDisplay()
        searchPage.inputSearch(searchKey)
        searchPage.assertInput(searchKey)
        takeScreenShot(testSSPath, "input_search_display")
    }
}