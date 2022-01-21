package features

import base.BaseUITest
import config.DeviceType
import config.TestConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.testng.annotations.Test
import util.Helper
import kotlin.test.currentStackTrace

class SearchResultTest : BaseUITest() {

    private val testSSPath = "search-result-test/"


    override fun getDriver(): AppiumDriver<out MobileElement> {
        val caps = TestConfig.androidCapabilities(DeviceType.Device1)
        return AndroidDriver(TestConfig.url, caps)
    }

    @Test
    fun test_result_1(){
        searchPage.waitToDisplay()
        searchPage.inputSearch("alvi")
        Helper.assertLog("first search result", searchPage.getItemText(0), "alvin2ye")
        takeScreenShot(testSSPath,"test_result_1")
    }

    @Test
    fun test_result_2(){
        searchPage.waitToDisplay()
        searchPage.inputSearch("alvinati")
        Helper.assertLog("first search result", searchPage.getItemText(0), "alvinatin")
        takeScreenShot(testSSPath, "test_result_2")
    }

}