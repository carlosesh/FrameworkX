package ui.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.WaitUtils;

public class CreateAccountPage {
    WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[contains(text(), 'Create account')]")
    WebElement createAccountHeader;

    @FindBy(name = "customerName")
    WebElement yourNameInputField;

    @FindBy(name = "email")
    WebElement emailInputField;

    @FindBy(xpath = "//a[text()='Conditions of Use']")
    WebElement conditionsOfUseLink;

    public void waitForCreateAccountHeaderToBiVisible() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(createAccountHeader));
    }

    public void sendKeysToYourNameInputField(String input) {
        yourNameInputField.sendKeys(input);
    }

    public void sendKeysToYourEmailInputField(String input) {
        emailInputField.sendKeys(input);
    }

    public void clickOnConditionsOfUseLink() {
        conditionsOfUseLink.click();
    }
}
