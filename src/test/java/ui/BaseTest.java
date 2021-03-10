package ui;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.ConfigFileReader;
import utils.report.utils.ReportUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private static ExtentReports reports;
    private static ExtentTest extentTest;
    WebDriver driver;
    String baseURL = new ConfigFileReader().getApplicationUrl();

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        reports = ReportUtils.initializeExtentReport();
    }

    @BeforeMethod
    public void setUpTest(ITestResult testResult) {
        extentTest = ReportUtils.startTest(reports, testResult);

        driver = new ChromeDriver(getChromeOptions());
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        initializeElements();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=2048,1080", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
        return options;
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws IOException {
        ReportUtils.logTestResult(testResult, driver, extentTest);
        quitDriver();
    }

    private void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void flushReports() {
        ReportUtils.flushReport(reports, extentTest);
    }

    public abstract void initializeElements();
}
