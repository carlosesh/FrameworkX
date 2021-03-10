package ui.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    public static void hoverOverElement(WebElement element, WebDriver driver) {
        String script = "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); " +
                "mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public static void clickWithJs(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
