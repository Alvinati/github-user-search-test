package config

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
    * 20,21
    */
   private val platformType = PlatformType.Android
   private val appLocation = "/Users/choir/Documents/AndroidProjects/Private/app/build/outputs/apk/debug/app-debug.apk"

   val url = URL("http://127.0.0.1:4723/wd/hub")

   const val SCREEN_WAIT_TIMEOUT = 60L

   fun getAppTestDriver(deviceType: DeviceType): AppiumDriver<out MobileElement> {
      return if(platformType == PlatformType.Android){
         val testInfo = getDeviceInfo(platformType, deviceType)
         getAndroidTestDriver(testInfo.udiid, testInfo.systemPort, testInfo.platformVersion)
      }else getIOSTestDriver()
   }

   fun androidCapabilities(deviceType: DeviceType) : DesiredCapabilities {
      val testInfo = getDeviceInfo(PlatformType.Android, deviceType)
      val capabilities = DesiredCapabilities()

      println("${testInfo.systemPort}")
      capabilities.apply {
        // setCapability(MobileCapabilityType.UDID, null)
         //  setCapability(MobileCapabilityType.DEVICE_NAME, udid)
         setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
         setCapability(MobileCapabilityType.PLATFORM_VERSION, testInfo.platformVersion)
         setCapability(MobileCapabilityType.APP, appLocation)
         setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
         setCapability(MobileCapabilityType.NO_RESET, true)
         setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 600000)
         setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, 600000)
      }
      return capabilities
   }

   private fun getAndroidTestDriver(udid: String, systemPort: Int, platformVersion: String) : AppiumDriver<out MobileElement>  {
      val capabilities =  DesiredCapabilities()
      capabilities.run {
          setCapability(MobileCapabilityType.UDID, udid)
       //  setCapability(MobileCapabilityType.DEVICE_NAME, udid)
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

   private fun getIOSTestDriver() : AppiumDriver<out MobileElement>  {
      val capabilities =  DesiredCapabilities()
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

   private fun getDeviceInfo(platformType: PlatformType, deviceType: DeviceType) : TestDeviceInfo {
     return when(platformType) {
        PlatformType.Android -> {
           when(deviceType){
              DeviceType.Device1 -> {
                 TestDeviceInfo("emulator-5554", 8201, "10")
              }
              DeviceType.Device2 -> {
                 TestDeviceInfo("emulator-5556", 8203, "9")
              }
           }
         }
        PlatformType.iOS -> {
            TestDeviceInfo("", 8100, "14.5")
         }
      }
   }
}