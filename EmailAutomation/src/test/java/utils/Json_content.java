package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import projectSpecifications.BaseClass;

public class Json_content extends BaseClass {
	//private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public Json_content(WebDriver driver) {
		// Initialize the driver and page elements
				TestContext.setDriver(driver);
				PageFactory.initElements(driver, this);
	}
    @DataProvider(name = "scholarshipData")
    public Object[][] scholarshipData() {
    
        return new Object[][] {
            {
                "MGFF Scholarship Application Form",
                "<h3>Thank you for submitting your application for the MGFF Scholarship. \n\nYou will receive an email with your Application Reference Number and a link to check your application status. \n\nPlease regularly check your application status online to stay updated on the Scholarship process. </h3>",
                "<h3>You have already completed this form.</h3>",
                "page1",
                "Instructions",
                "Applicant/Authorized Person - Please note that this Form may be Filled out, Signed and submitted by the persons listed below (these are listed in the order of Preference below)- A. Student with NGO Worker B. Student with Teacher/Principal C. Student with Parent Application To help us evaluate your requirement, please fill out this form and submit the form online. Applicants must be in the 12th Grade (SYJC) or higher as of Academic Year 2023-24 to apply for this scholarship. Questions/information: For questions and concerns related to this form, please contact us at contact@multiplesgff.com",
                "<h3>Applicant/Authorized Person</h3>\n\n<p>Please note that this Form may be Filled out, Signed and submitted by the persons listed below (these are listed in the order of Preference below):</p>\n\n<ul>\n  <li>A. Student with NGO Worker</li>\n  <li>B. Student with Teacher/Principal</li>\n  <li>C. Student with Parent Application</li>\n</ul>\n\n<p>To help us evaluate your requirement, please fill out this form and submit the form online. Applicants must be in the 12th Grade (SYJC) or higher as of Academic Year 2023-24 to apply for this scholarship.</p>\n\n<p><b>Questions/information:</b> For questions and concerns related to this form, please contact us at <a href=\"mailto:contact@multiplesgff.com\">contact@multiplesgff.com</a></p>",
                "I have read the instructions and I am ready to fill out the form",
                true, // Using boolean to represent the checkbox being checked
                "Item 1",
                "Yes"
            }
        };
    }
}
