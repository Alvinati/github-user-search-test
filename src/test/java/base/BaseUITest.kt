package base

import config.TestConfig
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import java.util.concurrent.TimeUnit

open class BaseUITest {

    protected val testDriver = TestConfig.getAppTestDriver()
    protected val uiWait = WebDriverWait(testDriver, TestConfig.SCREEN_WAIT_TIMEOUT)


    @BeforeTest
    fun prepare() {
        testDriver.manage()?.timeouts()?.implicitlyWait(90000, TimeUnit.MILLISECONDS)
        testDriver.resetApp()
    }

    @AfterTest
    fun tearDown() {
        testDriver.resetApp()
        testDriver.quit()
    }
}