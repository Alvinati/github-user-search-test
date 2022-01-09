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


open class BaseUITest(private val driver: AppiumDriver<out MobileElement>) {

    protected lateinit var uiWait : WebDriverWait
    protected lateinit var searchPage: SearchPage
    private val screenShotPath = "./ss/"

    @BeforeClass
    fun prepare() {
        uiWait = WebDriverWait(driver, TestConfig.SCREEN_WAIT_TIMEOUT)
        driver.manage()?.timeouts()?.implicitlyWait(90000, TimeUnit.MILLISECONDS)
        searchPage = SearchPage(driver)

    }

    @AfterClass
    fun tearDown() {
        driver.quit()
    }

    @Throws(IOException::class)
    fun takeScreenShot(path:String, fileName: String) {
        val srcFile: File = driver.getScreenshotAs(OutputType.FILE)
        val targetFile = File("$screenShotPath$path$fileName.jpg")
        FileUtils.copyFile(srcFile, targetFile)
    }
}