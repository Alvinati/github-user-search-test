package config

import data.Device
import data.DeviceInfo
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

object TestConfig {
    /**
     * WARNING!!
     * please be careful when moving these lines of code here, as these lines will be rewritten in bitrise
     * current lines modified in bitrise:
     * 21,22
     */
    private val platformType = PlatformType.Android
    private val appLocation =
        "/Users/choirunisatrialvinati/StudioProjects/gitsearch/app/build/outputs/apk/debug/app-debug.apk"

    val url = URL("http://127.0.0.1:4723/wd/hub")

    const val SCREEN_WAIT_TIMEOUT = 240L
    private val deviceInfo = DeviceInfo()

    fun getAppTestDriver(deviceType: Device): AppiumDriver<out MobileElement> {
        return if (platformType == PlatformType.Android) {
            val testInfo = deviceInfo.getDeviceInfo(deviceType)
            getAndroidTestDriver(testInfo.systemPort, testInfo.platformVersion)
        } else getIOSTestDriver()
    }

    fun androidCapabilities(deviceType: Device): DesiredCapabilities {
        val testInfo = deviceInfo.getDeviceInfo(deviceType)
        val capabilities = DesiredCapabilities()

        println("${testInfo.systemPort}")
        capabilities.apply {
            // setCapability(MobileCapabilityType.UDID, null)
            setCapability(MobileCapabilityType.DEVICE_NAME, testInfo.deviceName)
            setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
            setCapability(MobileCapabilityType.PLATFORM_VERSION, testInfo.platformVersion)
            setCapability(MobileCapabilityType.APP, appLocation)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
            setCapability(MobileCapabilityType.NO_RESET, true)
            setCapability("appium:appWaitForLaunch", false)
            setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 600000)
            setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, 600000)
        }
        return capabilities
    }

    private fun getAndroidTestDriver(systemPort: Int, platformVersion: String): AppiumDriver<out MobileElement> {
        val capabilities = DesiredCapabilities()
        capabilities.run {
            setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
            setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion)
            setCapability(MobileCapabilityType.APP, appLocation)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
            setCapability(MobileCapabilityType.NO_RESET, true)
            setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort)
            setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 600000)
            setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, 600000)
        }
        return AndroidDriver(url, capabilities)
    }

    private fun getIOSTestDriver(): AppiumDriver<out MobileElement> {
        val capabilities = DesiredCapabilities()
        capabilities.run {
            setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11")
            setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
            setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.5")
            setCapability(MobileCapabilityType.APP, appLocation)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")
            setCapability(MobileCapabilityType.NO_RESET, true)
            setCapability("useNewWDA", false)
        }
        return IOSDriver(url, capabilities)
    }

}
