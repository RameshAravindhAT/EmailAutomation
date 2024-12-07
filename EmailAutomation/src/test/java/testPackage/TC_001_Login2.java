package testPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

@Listeners(utils.CustomTestListener.class) // Custom listeners for reporting or test actions

public class TC_001_Login2 extends BaseClass {

	@BeforeClass
	public void testcasedetails() {

		TestContext.setSheetName("Logins");

	}

	@Test(dataProvider = "sendData")
	public void validate_login(String testNameDetails, String authorName, String category, String userName,
			String password, String scenario, String name, String role, String templateName, String desc)
			throws InterruptedException {
		// Initialize the test in Extent Reports using TestContext
		ExtentReportManager.setTest(extent.createTest(testNameDetails)); // Create the test instance in Extent Reports
		ExtentReportManager.getTest().assignAuthor(authorName); // Assign the author for the test
		ExtentReportManager.getTest().assignCategory(category); // Assign the category for the test

		// Perform the login action using the login page object
		TestContext.getLoginPage().Enter_User_Name(userName).Enter_Password(password).Click_Login_Button()
				.Verifiy_toast_message(scenario, name, role);

	}
}
