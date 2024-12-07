package testPackage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;
import utils.ExtentReportManager;
import utils.TestContext;

@Listeners(utils.CustomTestListener.class) // Custom listeners for reporting or test actions

public class TC_002_Template extends BaseClass {

	@BeforeClass
	public void testcasedetails() {

		TestContext.setSheetName("Template");

	}

	@Test(dataProvider = "sendData")	

	public void validate_login(String testNameDetails, String authorName, String category, String userName,
			String password, String scenario, String name, String role,String templateName, String desc) throws InterruptedException {
		// Initialize the test in Extent Reports using TestContext
		ExtentReportManager.setTest(extent.createTest(testNameDetails)); // Create the test instance in Extent Reports
		ExtentReportManager.getTest().assignAuthor(authorName); // Assign the author for the test
		ExtentReportManager.getTest().assignCategory(category); // Assign the category for the test

		// Perform the login action using the login page object
		TestContext.getLoginPage()
		         .Enter_User_Name(userName)
		         .Enter_Password(password)
	           	 .Click_Login_Button()
				.Verifiy_toast_message(scenario, name, role)
				.Open_Administration()
				.Open_Application()
				.Open_Application_Template()
				.Add_Template()
				.Enter_Template_and_Description(templateName, desc)
				.Click_JsonEditor_and_Paste_the_JsonContent()
				.Save_Template();
//				.Verifiy_toast_message(scenario, templateName, role)
//				.Verifiy_All_Template(scenario)
//				.Inactive_Tab()
//				.Edit_Template_and_Save()
//				.Double_click_Template();			
	}
}
