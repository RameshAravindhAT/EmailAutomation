package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

public class PG_002_Template2 extends BaseClass {

	public PG_002_Template2(WebDriver driver) {
		// Initialize the driver and page elements
		TestContext.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@onclick=\"toggleSubmenu('applicationsSubmenu')\"]//span[@class='fas fa-chevron-down']")
	public WebElement application;

	@FindBy(xpath = "//span[normalize-space()='Application Templates']")
	public WebElement applicationtemplate;

	@FindBy(xpath = "//button[normalize-space()='Add New Template']")
	public WebElement addtemplate;

	@FindBy(id = "id_name")
	public WebElement templatename;

	@FindBy(xpath = "//textarea[@id='id_description']")
	public WebElement description;

	@FindBy(xpath = "//span[contains(text(),'JSON Editor')]")
	public WebElement jsoncontent;

	@FindBy(xpath = "//textarea[@aria-label='JSON Editor']")
	public WebElement jsontextarea;

	@FindBy(xpath = "//span[@class='svc-text svc-text--normal svc-tabbed-menu-item__text svc-text--bold']")
	public WebElement preview;

	@FindBy(xpath = "//div[@class='col-md-12 d-flex justify-content-end']//button[@type='submit'][normalize-space()='Save']")
	public WebElement save;

	@FindBy(xpath = "//a[@id='inactive-tab']")
	public WebElement inactive;

	@FindBy(xpath = "//tbody/tr[1]/td[4]/a[1]")
	public WebElement edit;

	@FindBy(xpath = "//input[@id='id_is_active']")
	public WebElement isactive;

	public PG_002_Template2 Open_Application() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			application.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template2 Open_Application_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			applicationtemplate.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template2 Add_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			addtemplate.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template2 Enter_Template_Name(String templateName) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getWait().until(ExpectedConditions.visibilityOf(templatename));
			Thread.sleep(3000);
			templatename.sendKeys(templateName);
			ExtentReportManager.reportStep(methodName + " " + templateName, "pass");
			TestContext.getLogger().info(methodName + " " + templateName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + templateName);
			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template2 Enter_Description(String desc) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			description.sendKeys(desc);
			ExtentReportManager.reportStep(methodName + " " + desc, "pass");
			TestContext.getLogger().info(methodName + " " + desc);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName + " " + desc);
			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template2 Click_JsonEditor() throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getJsExecutor().scrollDownByPixels(350);
			Thread.sleep(3000);
			System.out.println("Scrolling is happening");
			TestContext.getWait().until(ExpectedConditions.visibilityOf(jsoncontent)).click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (ElementClickInterceptedException e) {
			TestContext.getJsExecutor().clickElementUsingJS(jsoncontent);
		} catch (Exception e)

		{
			e.printStackTrace();
			TestContext.getLogger().error(methodName);
		}

		return this;
	}

	public PG_002_Template2 Paste_Jsoncontent() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			TestContext.getWait().until(ExpectedConditions.elementToBeClickable(jsontextarea));
			jsontextarea.click();
			Thread.sleep(3000);
			jsontextarea.clear();
			Thread.sleep(8000);
			preview.click();
			Thread.sleep(3000);
			save.click();
			/*
			 * Json_content jsoncontent = new Json_content(TestContext.getDriver());
			 * Object[] json = jsoncontent.scholarshipData(); String dataToSend = ""; //
			 * Initialize an empty string to hold the data
			 * 
			 * for (Object obj : json) {
			 * 
			 * dataToSend += obj.toString() + " "; // Concatenate each item (separated by a
			 * space or custom separator) } jsontextarea.sendKeys(dataToSend);
			 * Thread.sleep(5000);
			 */

			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template2 Save_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			save.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template2 Verifiy_toast_message(String scenario, String name, String role)
			throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {
				String text = TestContext.getWait()
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//div//p[text()='Template added successfully']")))
						.getText();
				System.out.println(text);
				Assert.assertEquals(text, "Template added successfully");
			} else if (scenario.equalsIgnoreCase("negative")) {
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
				ExtentReportManager.reportStep(methodName, "pass");
				TestContext.getLogger().info(methodName);
			}
		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template2 Verifiy_All_Template(String scenario) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			if (scenario.equalsIgnoreCase("positive")) {
				String text = TestContext.getWait().until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/main/div[2]/div[1]/h2")))
						.getText();
				System.out.println(text);
				Assert.assertEquals(text, "All Templates");
			}
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}
		return this;
	}

	public PG_002_Template2 Inactive_Tab() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			inactive.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}

	public PG_002_Template2 Edit_Template() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

		try {
			Thread.sleep(2000);
			edit.click();
			Thread.sleep(2000);
			isactive.click();
			ExtentReportManager.reportStep(methodName, "pass");
			TestContext.getLogger().info(methodName);

		} catch (Exception e) {
			TestContext.getLogger().error(methodName);

			e.printStackTrace();
		}

		return this;
	}
	

}
