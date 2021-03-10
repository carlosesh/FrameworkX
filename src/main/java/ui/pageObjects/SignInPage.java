package ui.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.WaitUtils;

public class SignInPage {
    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[contains(text(), 'Sign-In')]")
    WebElement signInHeader;

    public void waitForSignInHeaderToBeDisplayed() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(signInHeader))
                .isDisplayed();
    }
}
