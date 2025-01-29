package com.pvs.cms.userManagement.contact.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.userManagement.contact.pageobject.ContactPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class ContactPageFunctions extends CommonActions {

	private WebDriver driver;
	private ContactPageLocators contactPageLocators;
	private static Logger logger = LogManager.getLogger(ContactPageFunctions.class);

	public ContactPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		contactPageLocators = new ContactPageLocators();
		PageFactory.initElements(driver, contactPageLocators);
	}


	public boolean searchContactByEmail(String email) {
		try {
			boolean res = false;
			getUrl(Constants.base_url + Constants.contact_url);
			logger.info("Searching contact with email: " + email);
			sendKeys(contactPageLocators.search, email, "Searching contact by email: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkEmail = match.findElement(By.xpath(".//td[4]")).getText(); // Assuming email is in the 4th column
				if (email.equalsIgnoreCase(checkEmail)) {
					res = true;
					break;
				}
			}
			return res;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching contact by email: " + ex);
			return false;
		}
	}


	public boolean addContact(String firstName, String lastName, String dob, String email, String mobile,
			String address1, String address2, String pincode, String city, String state, String country, String contactType) {
		try {
			logger.info("Adding contact");
			getUrl(Constants.base_url + Constants.contact_url);
			clickElement(contactPageLocators.addContact, "Add Contact");

			sendKeys(contactPageLocators.firstName, firstName, "First Name");
			sendKeys(contactPageLocators.lastName, lastName, "Last Name");
			sendKeys(contactPageLocators.dob, dob, "Date of Birth");
			sendKeys(contactPageLocators.email, email, "Email");
			sendKeys(contactPageLocators.mobile, mobile, "Mobile");
			sendKeys(contactPageLocators.address1, address1, "Address 1");
			sendKeys(contactPageLocators.address2, address2, "Address 2");
			sendKeys(contactPageLocators.pincode, pincode, "Pincode");

			selectFromDropdown(contactPageLocators.city, city, "City");
			selectFromDropdown(contactPageLocators.state, state, "State");
			selectFromDropdown(contactPageLocators.country, country, "Country");
			selectFromDropdown(contactPageLocators.contactType, contactType, "Contact Type");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (contactPageLocators.btnSubmit.isEnabled()) {
				clickElement(contactPageLocators.btnSubmit, "Submit Button");
				getTest().get().pass("Contact added successfully");
				logger.info("Contact added successfully");
				return true;
			} else {
				clickElement(contactPageLocators.btnCancel, "Cancel Button");
				getTest().get().error("Error: Contact not added");
				logger.error("Error: Contact not added");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while adding contact: " + ex);
			return false;
		}
	}


	public boolean editContact(String email, String newFirstName, String newLastName, String newDob, String newEmail,
			String newMobile, String newAddress1, String newAddress2, String newPincode, String newCity, String newState,
			String newCountry, String newContactType) {
		try {
			logger.info("Editing contact");
			getUrl(Constants.base_url + Constants.contact_url);
			WebElement row = searchContactAndReturnRow(email);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[5]//a")); // Assuming edit button is in the 5th column
				clickElement(editBtn, "Edit Button");

				sendKeys(contactPageLocators.firstName, newFirstName, "New First Name");
				sendKeys(contactPageLocators.lastName, newLastName, "New Last Name");
				sendKeys(contactPageLocators.dob, newDob, "New Date of Birth");
				sendKeys(contactPageLocators.email, newEmail, "New Email");
				sendKeys(contactPageLocators.mobile, newMobile, "New Mobile");
				sendKeys(contactPageLocators.address1, newAddress1, "New Address 1");
				sendKeys(contactPageLocators.address2, newAddress2, "New Address 2");
				sendKeys(contactPageLocators.pincode, newPincode, "New Pincode");

				selectFromDropdown(contactPageLocators.city, newCity, "New City");
				selectFromDropdown(contactPageLocators.state, newState, "New State");
				selectFromDropdown(contactPageLocators.country, newCountry, "New Country");
				selectFromDropdown(contactPageLocators.contactType, newContactType, "New Contact Type");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (contactPageLocators.btnSubmit.isEnabled()) {
					clickElement(contactPageLocators.btnSubmit, "Submit Button");
					getTest().get().pass("Contact updated successfully");
					logger.info("Contact updated successfully");
					return true;
				} else {
					clickElement(contactPageLocators.btnCancel, "Cancel Button");
					getTest().get().error("Error: Contact not updated");
					logger.error("Error: Contact not updated");
					return false;
				}
			} else {
				getTest().get().fail("No such contact found");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while editing contact: " + ex);
			return false;
		}
	}


	private WebElement searchContactAndReturnRow(String email) {
		try {
			logger.info("Searching and returning contact row by email");
			sendKeys(contactPageLocators.search, email, "Searching contact table by email: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkEmail = match.findElement(By.xpath(".//td[4]")).getText(); // Assuming email is in the 4th column
				if (email.equalsIgnoreCase(checkEmail)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching and returning contact row by email: " + ex);
			return null;
		}
	}


	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(contactPageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded Successfully");
			return true;
		} catch (Exception ex) {
			logger.info("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}
}
