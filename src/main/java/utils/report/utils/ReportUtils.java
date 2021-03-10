package utils.report.utils;

import com.relevantcodes.extentreports.ExtentReports;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportUtils {

    private static ExtentReports initializeExtentReport(ExtentReports reports) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "TestReport.html");
        return new ExtentReports(filePath.toString());
    }
}
