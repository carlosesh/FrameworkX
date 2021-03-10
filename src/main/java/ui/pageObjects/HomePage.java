package ui.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.JavaScriptUtils;
import ui.utils.WaitUtils;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-logo-sprites")
    private WebElement amazonPrimeLogo;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchIcon;

    @FindBy(xpath = "//span[contains(text(), 'Account & Lists')]")
    private WebElement accountAndListsIcon;

    @FindBy(xpath = "//div[@id='nav-flyout-ya-newCust']/a[text()='Start here.']")
    private WebElement startHereLink;

    public void sendKeysToSearchBox(String item) {
        searchBox.sendKeys(item);
    }

    public void waitForAmazonLogoToBeDisplayed() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(amazonPrimeLogo));
    }

    public void clickSearchIcon() {
        searchIcon.click();
    }

    public void clickNewCustomerStartHereLink() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.elementToBeClickable(startHereLink))
                .click();
    }

    public void hoverOverAccountsAndListsIcon() {
        JavaScriptUtils.hoverOverElement(accountAndListsIcon, driver);
    }
}
