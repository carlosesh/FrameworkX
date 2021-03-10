package ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CommonMethods {
    public static void validateIfListOfElementsAreDisplayed(List<String> elements, WebDriver driver)  {
        elements.forEach( element -> {
            driver.findElement(By.xpath("//*[contains(text(), '" + element + "')]")).isDisplayed();
        });
    }
}
