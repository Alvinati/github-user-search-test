package features

import base.BaseUITest
import config.TestConfig
import org.testng.annotations.Test
import util.Helper

class SearchResultTest : BaseUITest(TestConfig.getAppTestDriver()) {

    private val testSSPath = "search-result-test/"

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