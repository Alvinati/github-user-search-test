package model

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.support.ui.WebDriverWait

class WaitElement(driver: AppiumDriver<out MobileElement>) {
    val longWait = WebDriverWait(driver, 30L)
    val defaultWait = WebDriverWait(driver, 15L)
    val minimalWait = WebDriverWait(driver, 5L)
    val shortWait = WebDriverWait(driver, 2L)
}