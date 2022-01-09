package base

import config.TestConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.*
import page.SearchPage
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


abstract class BaseUITest {

    protected lateinit var testDriver: AppiumDriver<out MobileElement>
    protected lateinit var uiWait : WebDriverWait
    protected lateinit var searchPage: SearchPage
    private val screenShotPath = "./ss/"

    abstract fun getDriver() : AppiumDriver<out MobileElement>

    @BeforeClass
    fun prepare() {
        testDriver = getDriver()
        uiWait = WebDriverWait(testDriver, TestConfig.SCREEN_WAIT_TIMEOUT)
        testDriver.manage()?.timeouts()?.implicitlyWait(90000, TimeUnit.MILLISECONDS)
        searchPage = SearchPage(testDriver)
    }

    @AfterClass
    fun tearDown() {
        testDriver.quit()
    }

    @Throws(IOException::class)
    fun takeScreenShot(path:String, fileName: String) {
        val srcFile: File = testDriver.getScreenshotAs(OutputType.FILE)
        val targetFile = File("$screenShotPath$path$fileName.jpg")
        FileUtils.copyFile(srcFile, targetFile)
    }
}