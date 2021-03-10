package ui;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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
import utils.Screenshots;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private static ExtentReports reports;
    private static ExtentTest test;
    WebDriver driver;
    String baseURL = new ConfigFileReader().getApplicationUrl();

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        initializeExtentReport();
    }

    private static void initializeExtentReport() {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "TestReport.html");
        reports = new ExtentReports(filePath.toString());
    }

    @BeforeMethod
    public void setUpTest(ITestResult testResult) {
        test = reports.startTest(getMethodName(testResult));

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
        logTestResult(testResult);
        quitDriver();
    }

    private void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void logTestResult(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == testResult.FAILURE) {
            String path = Screenshots.takeScreenshot(driver, getMethodName(testResult));
            String imagePath = test.addScreenCapture(path);
            test.log(LogStatus.FAIL, "Test: " + getMethodName(testResult) + " Failed", imagePath);
        } else {
            test.log(LogStatus.PASS, "Test: " + getMethodName(testResult) + " Passed");
        }
    }

    private String getMethodName(ITestResult testResult) {
        return testResult.getMethod().getMethodName();
    }

    @AfterClass
    public void flushReports() {
        reports.endTest(test);
        reports.flush();
    }

    public abstract void initializeElements();
}
