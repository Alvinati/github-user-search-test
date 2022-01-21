package config

data class TestDeviceInfo(
    var deviceName: String? = null,
    var udiid: String? = null,
    val systemPort : Int,
    val platformVersion : String
)
