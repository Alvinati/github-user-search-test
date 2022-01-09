package page

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import model.Direction
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import util.Helper
import util.PlatformTouchAction
import util.getCoordinate
import java.time.Duration
import kotlin.test.fail

class SearchPage(private val driver: AppiumDriver<out MobileElement>) {

    companion object {
        const val ACC_ID_TITLE_VIEW = "main.label_title"
        const val ACC_ID_SEARCH_VIEW = "main.input_search"
        const val ACC_ID_SEARCH_LAYOUT = "main.layout_search"
        const val ACC_ID_ITEM_VIEW = "main.item_container"
        const val ACC_ID_ITEM_LINE_SEPARATOR = "main.view_line_separator"
        const val ACC_ID_ITEM_NAME = "main.item_lbl_name"
    }

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    @AndroidFindBy(accessibility = ACC_ID_ITEM_VIEW)
    private lateinit var elementItem : WebElement

    @AndroidFindBy(accessibility = ACC_ID_ITEM_LINE_SEPARATOR)
    private lateinit var elementLineSeparator : WebElement

    @AndroidFindBy(accessibility = ACC_ID_SEARCH_LAYOUT)
    private lateinit var elementSearchLayout : WebElement

    @AndroidFindBy(accessibility = ACC_ID_SEARCH_VIEW)
    private lateinit var elementFieldSearch : WebElement

    @AndroidFindBy(accessibility = ACC_ID_ITEM_NAME)
    private lateinit var elementItemName : WebElement

    @AndroidFindBy(accessibility = ACC_ID_TITLE_VIEW)
    private lateinit var elementTitle : WebElement

    private val wait = WebDriverWait(driver, 15L)

    fun waitToDisplay() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.AccessibilityId(ACC_ID_TITLE_VIEW)))
    }

    fun assertInput(text: String){
        Helper.assertLog("assert field text", elementFieldSearch.text, text)
    }

    fun inputSearch(searchKey: String) {
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(ACC_ID_SEARCH_VIEW)))
        elementFieldSearch.clear()
        elementFieldSearch.sendKeys(searchKey)
    }

    fun scrollItemToTop(indexItem: Int) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.AccessibilityId(ACC_ID_ITEM_VIEW)))
        val lineSeparators = driver.findElementsByAccessibilityId(ACC_ID_ITEM_LINE_SEPARATOR)
        val itemCount = lineSeparators.size
        val endCoordinate = elementTitle.getCoordinate()

        val itemNames = driver.findElementsByAccessibilityId(ACC_ID_ITEM_NAME)
        println("SCROLLED ITEM NAME = ${itemNames[indexItem].text}")

        if(indexItem < itemCount){
            swipeElement(lineSeparators[indexItem],
                endCoordinate.middleX,
                endCoordinate.middleY)
        }else fail("index item not found")
    }

    fun getItemText(indexItem: Int) : String {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.AccessibilityId(ACC_ID_ITEM_NAME)))
        val itemNames = driver.findElementsByAccessibilityId(ACC_ID_ITEM_NAME)
        return itemNames[indexItem].text
    }

    private fun swipeElement(element: WebElement, pointEndX: Int, pointEndY: Int) {
        val elementCoordinate = element.getCoordinate()

        val pointStart = Point(elementCoordinate.middleX, elementCoordinate.lowerY)
        val pointEnd = Point(pointEndX, pointEndY)

        performSwipe(pointStart, pointEnd)
    }

    fun swipeScreen(dir: Direction, additionalPointMove : Int = 0) {
        println("swipeScreen(): dir: '$dir'") // always log your actions

        val edgeBorder = 10 // better avoid edges
        val pointStart: Point
        val pointEnd: Point

        // init screen variables
        val dims: Dimension = driver.manage().window().size

        // init start point = center of screen
        pointStart = Point(dims.width / 2, dims.height / 2)
        pointEnd = when (dir) {
            Direction.DOWN -> Point(dims.width / 2, dims.height - edgeBorder + additionalPointMove)
            Direction.UP -> Point(dims.width / 2, edgeBorder + additionalPointMove)
            Direction.LEFT -> Point(edgeBorder + additionalPointMove, dims.height / 2)
            Direction.RIGHT -> Point(dims.width - edgeBorder + additionalPointMove, dims.height / 2)
            else -> throw IllegalArgumentException(
                "swipeScreen(): dir: '" + dir.toString().toString() + "' NOT supported"
            )
        }

        performSwipe(pointStart, pointEnd)
    }

    private fun performSwipe(pointStart: Point, pointEnd: Point) {
        // final value depends on your app and could be greater
        val animationTime = 200 // ms
        val pressTime = 300L // ms
        val edgeBorder = 10 // better avoid edges

        // execute swipe using TouchAction
        val pointOptionStart: PointOption<*> = PointOption.point(pointStart.x, pointStart.y)
        val pointOptionEnd: PointOption<*> = PointOption.point(pointEnd.x, pointEnd.y)
//        println("swipeScreen(): pointStart: {" + pointStart.x + "," + pointStart.y + "}")
//        println("swipeScreen(): pointEnd: {" + pointEnd.x + "," + pointEnd.y + "}")
//        System.out.println("swipeScreen(): screenSize: {" + dims.width.toString() + "," + dims.height.toString() + "}")
        try {
            PlatformTouchAction(driver)
                .longPress(pointOptionStart) // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime)))
                .moveTo(pointOptionEnd)
                .release().perform()
        } catch (e: Exception) {
            System.err.println(
                """
                swipeScreen(): TouchAction FAILED
                ${e.message}
                """.trimIndent()
            )
            return
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(animationTime.toLong())
        } catch (e: InterruptedException) {
            // ignore
        }
    }

}