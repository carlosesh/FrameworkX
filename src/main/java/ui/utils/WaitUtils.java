package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtils {

    public static FluentWait<WebDriver> fluentWait(int timeOut, int pollingEvery, WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(Exception.class);
    }
}
