package features

import base.BaseUITest
import config.TestConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.testng.annotations.Test
import util.Helper

class SearchResultTest : BaseUITest() {

    private val testSSPath = "search-result-test/"


    override fun getDriver(): AppiumDriver<out MobileElement> {
        return TestConfig.getAppTestDriver()
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