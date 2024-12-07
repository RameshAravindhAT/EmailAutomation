package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestAutomation {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void runTests() {
        // Setup Extent Reports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent_report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        test = extent.createTest("Selenium Test", "Automated test triggered by email");

        // Start Selenium WebDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver"); // Path to your chromedriver
        WebDriver driver = new ChromeDriver();
        
        try {
            // Example: Open a website and perform a search
            driver.get("https://www.example.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Selenium Test");
            searchBox.submit();

            // Verify test result
            if (driver.getTitle().contains("Selenium Test")) {
                test.pass("Test Passed");
            } else {
                test.fail("Test Failed");
            }
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        } finally {
            driver.quit();  // Ensure browser is closed after the test
            extent.flush();  // Save the report
        }
    }
}

