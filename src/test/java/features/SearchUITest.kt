package features

import base.BaseUITest
import config.TestConfig
import org.testng.annotations.Test

class SearchUITest : BaseUITest(TestConfig.getAppTestDriver()) {


    private val testSSPath = "search-ui-test/"

    private val searchKey = "alvinati"
    private val hint = "Search for name.."


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