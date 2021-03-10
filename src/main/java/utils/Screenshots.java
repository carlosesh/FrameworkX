package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Screenshots {

    public static String takeScreenshot(WebDriver driver, String filename) throws IOException {
        filename = filename.concat(".png");
        Path directory = Paths.get(System.getProperty("user.dir"));
        Path screenshotDirectory = Paths.get(directory.toString(), "screenshots");
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path sc = Paths.get(screenshotDirectory.toString(), filename);
        FileUtils.copyFile(sourceFile, new File(sc.toString()));

        return sc.toString();
    }
}
