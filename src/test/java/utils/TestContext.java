package utils;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import pageObjects.PG_001_Login;

public class TestContext {

    // ThreadLocal variables for WebDriver, WebDriverWait, JavascriptExecutor, ExtentTest, etc.
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    private static ThreadLocal<JavascriptExecutor> jsExecutorThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<String> sheetName = new ThreadLocal<>();
    private static ThreadLocal<PG_001_Login> login = new ThreadLocal<>();
    
    private static final Logger logger = Logger.getLogger(TestContext.class);


    // Getter and Setter for WebDriver
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Getter and Setter for WebDriverWait
    public static WebDriverWait getWait() {
        return wait.get();
    }

    public static void setWait(WebDriver driverInstance) {
        // Create a new WebDriverWait instance with a 15-second timeout
        WebDriverWait waitInstance = new WebDriverWait(driverInstance, Duration.ofSeconds(30));
        wait.set(waitInstance);  // Correctly set the WebDriverWait instance in the ThreadLocal
    }


    // Getter and Setter for JavascriptExecutor
    public static Scroll_Actions getJsExecutor() {
        return new Scroll_Actions(TestContext.getDriver()); 
       
    }

    public static void setJsExecutor(JavascriptExecutor jsExecutor) {
        jsExecutorThreadLocal.set(jsExecutor);
    }

    // Getter and Setter for SheetName
    public static String getSheetName() {
        return sheetName.get();
    }

    public static void setSheetName(String sheet) {
        sheetName.set(sheet);
    }

    // Getter and Setter for Login Page Object
    public static PG_001_Login getLoginPage() {
        return login.get();
    }

    public static void setLoginPage(PG_001_Login loginPage) {
        login.set(loginPage);
    }
    
    public static Logger getLogger() {
        return logger;
    }
    

}
