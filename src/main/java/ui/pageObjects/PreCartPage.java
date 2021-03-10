package ui.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.WaitUtils;

public class PreCartPage {
    WebDriver driver;

    public PreCartPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[contains(text(), 'Added to Cart')]")
    WebElement addedToCartHeader;

    @FindBy(xpath = "//a[@role='button' and @id='hlb-view-cart-announce']")
    WebElement cartButton;

    public void waitForAddedToCartHeaderToBeDisplayed() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(addedToCartHeader))
                .isDisplayed();
    }

    public void clickCartButton() {
        cartButton.click();
    }
}
