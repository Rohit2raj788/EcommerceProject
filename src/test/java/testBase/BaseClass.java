package testBase;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ExtentReportUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseClass {
    public WebDriver driver;
    public Logger logger;
    public  Properties po;
    public Actions act;

    @BeforeClass
    @Parameters({"browsers", "operatingSystem"})
    public void setUp(String br, String OS) throws IOException {
        logger= LogManager.getLogger(this.getClass());
        FileInputStream config= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
        po= new Properties();
        po.load(config);


        switch (br.toLowerCase()){
            case "chrome": driver= new ChromeDriver(); break;
            case "edge" : driver= new EdgeDriver();break;
            case "firefox": driver= new FirefoxDriver(); break;
            default: System.out.println("Invalid Browser");return;
        }
        act= new Actions(driver);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(po.getProperty("url"));
        config.close();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    
    @AfterSuite
    public void generateReport() {
        ExtentReportUtils.flushReport();  // Save and generate the report
    }
    @BeforeSuite
    public void setupReport() {
        ExtentReportUtils.initReport();  // Initialize Extent Report
    }
    @BeforeMethod
    public void startTestMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportUtils.startTest(testName);  // Automatically starts Extent Report test
        logger.info("**** Starting Test: " + testName + " ****");
        ExtentReportUtils.logInfo("Starting Test: " + testName);
    }

    @AfterMethod
    public void captureTestResult(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportUtils.logPass(testName + " Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportUtils.logFailWithScreenshot(driver, testName + " Failed: " + result.getThrowable(), testName);
        } else {
            ExtentReportUtils.logWarning(testName + " Skipped");
        }
    }


}
