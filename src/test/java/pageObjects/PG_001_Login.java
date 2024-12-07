package pageObjects;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

public class PG_001_Login extends BaseClass {

	public PG_001_Login(WebDriver driver) {
		// Initialize the driver and page elements
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "id_email")
	public WebElement username;

	@FindBy(id = "id_password")
	public WebElement password;

	@FindBy(xpath = "//button[@name='login']")
	public WebElement loginbutton;

	@FindBy(xpath = "//div[@class= 'toast-container']//div/p")
	public WebElement toastmessage;

	@FindBy(xpath = "//div[@class='h5 font-weight-normal mb-2']")
	public WebElement homepage;

	@FindBy(xpath = "//i[@class='fas fa-user-circle']")
	public WebElement Profile;

	@FindBy(xpath = "//span[@onclick=\"toggleSubmenu('adminSubmenu')\"]//span[@class='fas fa-chevron-down']")
	public WebElement administration;
	

	@FindBy(xpath = "//i[@class='fas fa-user-circle']")
	public WebElement profile;	
	
	@FindBy(xpath = "//a[@class='dropdown-item font-weight-bold']")
	public WebElement logout;
	


	public PG_001_Login Enter_User_Name(String userName) {
		ExtentReportManager.reportStep("Opening the Browser", "pass");
		ExtentReportManager.reportStep("Navigating to the URL"+properties.getProperty("url"), "pass");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			username.sendKeys(userName);
			ExtentReportManager.reportStep(methodName + " " + userName, "pass");
			TestContext.getLogger().info(methodName + " " + userName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + userName);
			e.printStackTrace();
		}
		return this;
	}

	public PG_001_Login Enter_Password(String passwordvales) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			password.sendKeys(passwordvales);
			ExtentReportManager.reportStep(methodName + " " + passwordvales, "pass");
			TestContext.getLogger().info(methodName + " " + passwordvales);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + passwordvales);

			e.printStackTrace();
		}
		return this;
	}

	public PG_001_Login Click_Login_Button() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			loginbutton.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	public PG_001_Login Verifiy_toast_message(String scenario, String name, String role) throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {

				String text = TestContext.getWait()
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//div[@class='h5 font-weight-normal mb-2']")))
						.getText();
				Thread.sleep(3000);
				System.out.println(text);
				Assert.assertEquals(text, "Hi " + name + " ! Welcome To MGFF " + role + " Dashboard");

			} else if (scenario.equalsIgnoreCase("negative")) {
				String text = TestContext.getWait()
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//div[@class= 'toast-container']//div/p")))
						.getText();
				System.out.println(text);
				Assert.assertEquals(text, "Invalid email or password.");

			} else if (scenario.equalsIgnoreCase("emailEmpty")) {
				WebElement toastMessage = TestContext.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Please fill in this field.')]")));

				// Retrieve the text of the toast message
				String messageText = toastMessage.getText();

				// Verify the message if necessary
				if (messageText.equals("Please fill in this field.")) {
					System.out.println("Toast message displayed correctly: " + messageText);
				} else {
					System.out.println("Unexpected toast message: " + messageText);
				}

			}
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template Open_Administration() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			administration.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return new PG_002_Template(TestContext.getDriver());
	}
	
	
	public PG_003_Logout Click_Logout() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			profile.click();
			TestContext.getWait().until(ExpectedConditions.elementToBeClickable(logout)).click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return new PG_003_Logout(TestContext.getDriver());
	}
	


}
