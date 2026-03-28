package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilities.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {

    private static WebDriver WebDriver;
    private static Properties properties;
    protected static ExtentReports extentReport;
    protected static ExtentTest extentTest;
    protected static WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BaseClass.class);

    protected static WebDriver getDriver() {
        return WebDriver;
    }

    public BaseClass(){
            try {
                properties = new Properties();
                FileInputStream fis = new FileInputStream("D:\\Cursor-Workspace\\Selenium\\Amazon\\src\\main\\resource\\config.properties");
                properties.load(fis);
            } catch (Exception e) {
               e.printStackTrace();
               System.out.println("Config File not found..");
            }
    }

    @BeforeTest
    public ExtentReports setReport() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
                "D:\\Cursor-Workspace\\Selenium\\Amazon\\target\\Reports\\AmazonReports.html");
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Author","Ashutosh Singh");
        extentReport.setSystemInfo("ProjectName","Amazon");
        extentReport.setSystemInfo("Tools","Automation_Selenium");
        extentReport.setSystemInfo("Project", "webBased");
        return extentReport;
    }

    @BeforeMethod
    public static void start(){
       String BrowserName=properties.getProperty("Browser");
       if(BrowserName.equals("chrome")){
        ChromeOptions opt = new ChromeOptions();
        WebDriver driver = new ChromeDriver();
        opt.addArguments("--disable-notifications");
        opt.addArguments("--disable-infobars");
		opt.addArguments("--disable-extensions");
		opt.addArguments("--disable-popup-blocking");
        logger.info("Chrome Browser is started");
       }
       else if(BrowserName.equals("firefox")){
        FirefoxOptions opt = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver();
        opt.addArguments("--disable-notifications");
        opt.addArguments("--disable-infobars");
        opt.addArguments("--disable-extensions");
        opt.addArguments("--disable-popup-blocking");
        logger.info("Firefox Browser is started");
       }
       else if(BrowserName.equals("edge")){
        EdgeOptions opt = new EdgeOptions();
        WebDriver driver = new EdgeDriver();
        opt.addArguments("--disable-notifications");
        opt.addArguments("--disable-infobars");
        opt.addArguments("--disable-extensions");
        opt.addArguments("--disable-popup-blocking");
        logger.info("Edge Browser is started");
       }else{
        System.out.println("Invalid Browser Name");
        logger.error("Invalid Browser Name");
       }
       WebDriver.get(properties.getProperty("url"));
       WebDriver.manage().window().maximize();
       WebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       WebDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
       logger.info("Browser is started and URL is loaded");
    }

    @AfterMethod
    public static void setStatus(ITestResult result){
          if(result.getStatus()==ITestResult.SUCCESS){
                extentTest.pass("Test passed");
          }else if(result.getStatus()==ITestResult.FAILURE){
            extentTest.addScreenCaptureFromPath(utility.getScreenshot(result.getName()));
            extentTest.fail(result.getThrowable());
          }else if(result.getStatus()==ITestResult.SKIP){
            extentTest.skip("Test skipped");
          }
          logger.info("Test status is set");
        }
}
