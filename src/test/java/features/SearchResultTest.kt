package features

import base.BaseUITest
import io.appium.java_client.MobileBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.testng.annotations.Test
import page.SearchPage
import util.Helper

class SearchResultTest : BaseUITest() {


    @Test
    fun test_result_1(){
        searchPage.waitToDisplay()
        searchPage.inputSearch("alvi")
        Helper.assertLog("first search result", searchPage.getItemText(0), "alvin2ye")
        takeScreenShot("test_result_1")
    }

    @Test
    fun test_result_2(){
        searchPage.waitToDisplay()
        searchPage.inputSearch("alvinati")
        Helper.assertLog("first search result", searchPage.getItemText(0), "alvinatin")
        takeScreenShot("test_result_2")
    }
}