package util

import org.testng.Assert
import org.testng.Reporter

object Helper {
    fun assertLog(assertName: String, actual: String, expected: String) {
        Reporter.log("Asserting on $assertName ::\n Actual: $actual\n Expected: $expected")
        println("Asserting on $assertName ::\n Actual: $actual\n Expected: $expected")
        Assert.assertEquals(actual, expected)
    }
}