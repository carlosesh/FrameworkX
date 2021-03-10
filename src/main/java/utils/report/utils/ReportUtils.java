package utils.report.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.Screenshots;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportUtils {

    public static ExtentReports initializeExtentReport() {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "TestReport.html");
        return new ExtentReports(filePath.toString());
    }

    public static ExtentTest startTest(ExtentReports report, ITestResult testResult) {
        return report.startTest(getMethodName(testResult));
    }

    public static void logTestResult(ITestResult testResult, WebDriver driver, ExtentTest extentTest) throws IOException {
        if (testResult.getStatus() == testResult.FAILURE) {
            String path = Screenshots.takeScreenshot(driver, getMethodName(testResult));
            String imagePath = extentTest.addScreenCapture(path);
            extentTest.log(LogStatus.FAIL, "Test: " + getMethodName(testResult) + " Failed", imagePath);
        } else {
            extentTest.log(LogStatus.PASS, "Test: " + getMethodName(testResult) + " Passed");
        }
    }

    public static void flushReport(ExtentReports reports, ExtentTest extentTest) {
        reports.endTest(extentTest);
        reports.flush();
    }

    private static String getMethodName(ITestResult testResult) {
        return testResult.getMethod().getMethodName();
    }
}
