package util

import model.ElementCoordinate
import org.openqa.selenium.WebElement


fun WebElement.getCoordinate() : ElementCoordinate {
    val leftX = this.location.x
    val rightX = leftX + this.size.width
    val middleX = (leftX+rightX)/2
    val upperY = this.location.y
    val lowerY = upperY + this.size.height //because 0 point of Y start from upper side of screen
    val middleY = (upperY+lowerY)/2

    return ElementCoordinate(leftX, rightX, middleX, upperY, lowerY, middleY)
}