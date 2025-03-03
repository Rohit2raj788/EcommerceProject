package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtils {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter sparkReporter;
    private static String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + getTimestamp() + ".html";


    // ✅ Initialize Extent Report
    public static void initReport() {
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // ✅ Start a Test
    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + getTimestamp() + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotPath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public static void logFailWithScreenshot(WebDriver driver, String message, String testName) {
        String screenshotPath = captureScreenshot(driver, testName);

        test.fail(message);  // ✅ Log failure message first
        test.addScreenCaptureFromPath("../screenshots/" + new File(screenshotPath).getName());  // ✅ Attach screenshot
    }

    // ✅ Logging Methods
    public static void logInfo(String message) {
        test.info(message);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }

    public static void logWarning(String message) {
        test.warning(message);
    }

    // ✅ Flush Report
    public static void flushReport() {
        extent.flush();
    }

    // ✅ Generate Timestamp for Unique Files
    private static String getTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }
}
