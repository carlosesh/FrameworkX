package ui.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.WaitUtils;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[contains(text(), 'Shopping Cart')]")
    WebElement shoppingCartHeader;

    @FindBy(xpath = "//div[@class='sc-list-item-content']//span[contains(@class, 'sc-product-price')]")
    WebElement productPrice;

    @FindBy(name = "proceedToRetailCheckout")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//input[@data-action='delete']")
    WebElement deleteFromCartLink;

    @FindBy(xpath = "//*[contains(text(), 'Cart is empty')]")
    WebElement emptyCartHeader;

    public boolean waitForShoppingCartHeaderToBeDisplayed() {
        return WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(shoppingCartHeader))
                .isDisplayed();
    }

    public void waitForEmptyCartHeaderToBeDisplayed() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(emptyCartHeader))
                .isDisplayed();
    }

    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText().replace("$", ""));
    }

    public void clickProceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public void clickDeleteLink() {
        deleteFromCartLink.click();
    }
}
