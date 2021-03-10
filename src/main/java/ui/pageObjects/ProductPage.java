package ui.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.JavaScriptUtils;
import ui.utils.WaitUtils;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "productTitle")
    private WebElement productTitle;

    @FindBy(id = "priceblock_ourprice")
    private WebElement productPrice;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[@data-action='a-popover-close']")
    private WebElement closePopOverButton;

    @FindBy(xpath = "//*[contains(@id, 'NoCoverage-announce')]")
    private WebElement noThanksButton;

    public void waitForProductTitleToBeVisible() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(productTitle));
    }


    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText().replace("$", ""));
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void waitForNoThanksButtonToBeVisible() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(noThanksButton));
    }

    public void clickNoThanksButtonToDeclineCoverage() {
        waitForNoThanksButtonToBeVisible();
        JavaScriptUtils.clickWithJs(noThanksButton, driver);

    }
}
