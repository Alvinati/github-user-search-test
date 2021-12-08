package config

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

/**
 * WARNING!!
 * please be careful when moving any line of code here as these lines will be rewritten in bitrise
 */
object TestConfig {
   private val platformType = PlatformType.iOS

   private val url = URL("http://127.0.0.1:4723/wd/hub")

   const val SCREEN_WAIT_TIMEOUT = 30L

   fun getAppTestDriver(): AppiumDriver<out MobileElement> {
      return if(platformType == PlatformType.Android){
         getAndroidTestDriver()
      }else getIOSTestDriver()
   }

   private fun getAndroidTestDriver() : AppiumDriver<out MobileElement>  {
      val capabilities =  DesiredCapabilities()
      capabilities.run {
         setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator")
         setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
         setCapability(MobileCapabilityType.PLATFORM_VERSION, "10")
         setCapability(MobileCapabilityType.APP, System.getenv("BITRISE_APK_PATH"))
         //setCapability(MobileCapabilityType.APP, "/Users/choir/Downloads/release/app-release.apk")
         setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
         setCapability("androidInstallTimeout", 600000)
         setCapability("adbExecTimeout", 600000)
      }
      return AndroidDriver(url, capabilities)
   }

   private fun getIOSTestDriver() : AppiumDriver<out MobileElement>  {
      val capabilities =  DesiredCapabilities()
      capabilities.run {
         setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11")
         setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
         setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.5")
         setCapability(MobileCapabilityType.APP, System.getenv("BITRISE_APP_DIR_PATH"))
         setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")
         setCapability("useNewWDA", false)
      }
      return IOSDriver(url, capabilities)
   }
}