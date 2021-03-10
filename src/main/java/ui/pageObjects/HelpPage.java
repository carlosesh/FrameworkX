package ui.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.utils.WaitUtils;

public class HelpPage {
    WebDriver driver;

    public HelpPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[text()='All Help Topics']")
    WebElement allHelpTopicsLink;

    @FindBy(xpath = "//h1[text()='Conditions of Use']")
    WebElement conditionsOfUseHeader;

    @FindBy(xpath = "//h1[contains(text(), 'Hello. What can we help you with?')]")
    WebElement whatCanWeHelpHeader;

    @FindBy(id = "helpsearch")
    WebElement helpSearchBar;

    public void clickAllHelpTopicsLink() {
        allHelpTopicsLink.click();
    }

    public void waitForConditionsOfUseHeaderToBeVisible() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(conditionsOfUseHeader));
    }

    public void waitForWhatCanWeHelpHeaderToBeVisible() {
        WaitUtils
                .fluentWait(30, 5, driver)
                .until(ExpectedConditions.visibilityOf(whatCanWeHelpHeader));
    }

    public void sendKeysToSearchBar(String input) {
        helpSearchBar.sendKeys(input);
        helpSearchBar.submit();
    }

    public void clickOnTopicLink(String topic) {
        WebElement topicLink = driver.findElement(By.xpath("//a[contains(text(), '" + topic + "') " +
                "and @class='a-link-normal']"));
        topicLink.click();
    }
}
