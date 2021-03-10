package ui.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.WaitUtils;

public class SearchResults {
    WebDriver driver;

    public SearchResults(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getProductElement(String product) {
        return driver.findElement(By.xpath("(//span[starts-with(text(), '" + product + "') and contains(@class, 'a-text-normal')])[1]"));
    }

    public void waitForProductToBeDisplayed(String product) {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(getProductElement(product)));
    }

    public void clickOnProduct(String product) {
        getProductElement(product).click();
    }

    public Double getPriceOfProduct(String product) {
        WebElement productPrice = driver.findElement(By.xpath("(//span[starts-with(text(), '" + product + "')" +
                " and contains(@class, 'a-text-normal')])[1]/ancestor::div[@class='sg-row']" +
                "//span[@class='a-price']/child::span[@class='a-offscreen']"));

        return Double.parseDouble(productPrice.getAttribute("innerHTML").replace("$", ""));
    }
}
