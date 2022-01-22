package data

class DeviceInfo {

   fun getDeviceInfo(device: Device) : TestDeviceInfo {
      return when(device) {
           Device.GooglePixel3a -> {
               TestDeviceInfo(udiid = "localhost:4321", systemPort = 8201, platformVersion = "10")
           }
          Device.GooglePixel2aXL -> {
              TestDeviceInfo(udiid = "localhost:4322", systemPort = 8202, platformVersion = "9")
          }
          Device.IPhone11 -> {
              TestDeviceInfo(udiid = "localhost:4322", systemPort = 8202, platformVersion = "14.5")
          }
       }
   }

}

enum class Device {
    GooglePixel3a,
    GooglePixel2aXL,
    IPhone11
}

data class TestDeviceInfo(
    var deviceName: String? = null,
    var udiid: String? = null,
    val systemPort : Int,
    val platformVersion : String
)
