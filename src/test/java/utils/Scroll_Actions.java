package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Scroll_Actions extends TestContext {
	
	
	public Scroll_Actions(WebDriver driver) {
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

    // This method will scroll the page to the bottom
    public void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) TestContext.getDriver();
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // This method will scroll the page to the top
    public void scrollToTop() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) TestContext.getDriver();
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    // This method will scroll the page to a specific element
    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) TestContext.getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // This method will scroll the page by a certain number of pixels (e.g., 500px)
    public void scrollByPixels(int x, int y) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) TestContext.getDriver();
        jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
    
    public void scrollDownByPixels(int pixels) {
        // Cast WebDriver to JavascriptExecutor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) TestContext.getDriver();

        // Execute JavaScript to scroll the page vertically by the specified number of pixels
        jsExecutor.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }
    
    public void clickElementUsingJS(WebElement element) {
        // Cast WebDriver to JavascriptExecutor
    	 JavascriptExecutor jsExecutor = (JavascriptExecutor) TestContext.getDriver();

        // Execute JavaScript to click the element
        jsExecutor.executeScript("arguments[0].click();", element);
    }
}
