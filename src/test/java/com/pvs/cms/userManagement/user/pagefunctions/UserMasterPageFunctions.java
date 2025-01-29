package com.pvs.cms.userManagement.user.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.userManagement.user.pageobject.UserMasterPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class UserMasterPageFunctions extends CommonActions {

	private WebDriver driver;
	private UserMasterPageLocators userMasterPageLocators;
	private static Logger logger = LogManager.getLogger(UserMasterPageFunctions.class);

	public UserMasterPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		userMasterPageLocators = new UserMasterPageLocators();
		PageFactory.initElements(driver, userMasterPageLocators);
	}

	// Function to search for a user by username
	public boolean searchUser(String username) {
		try {
			boolean userFound = false;
			getUrl(Constants.base_url + Constants.user_url);
			logger.info("Searching for user with username: " + username);
			sendKeys(userMasterPageLocators.search, username, "Searching user table: ");

			for (WebElement row : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkUsername = row.findElement(By.xpath(".//td[1]")).getText();
				if (username.equalsIgnoreCase(checkUsername)) {
					userFound = true;
					break;
				}
			}
			return userFound;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching for user: " + ex);
			return false;
		}
	}

	// Function to add a new user
	public boolean addUser(String username, String contact, String userRole) {
		try {
			logger.info("Adding a new user with username: " + username);
			getUrl(Constants.base_url + Constants.user_url);
			clickElement(userMasterPageLocators.addUser, "Add User");

			sendKeys(userMasterPageLocators.username, username, "Username");

			selectFromDropdown(userMasterPageLocators.contact, contact, "Contact");
			selectFromDropdown(userMasterPageLocators.userRole, userRole, "User Role");

			if (userMasterPageLocators.btnSubmit.isEnabled()) {
				clickElement(userMasterPageLocators.btnSubmit, "Submit Button");
				getTest().get().pass("User added successfully");
				logger.info("User added successfully: " + username);
				return true;
			} else {
				clickElement(userMasterPageLocators.btnCancel, "Cancel Button");
				getTest().get().error("Failed to add user");
				logger.error("Failed to add user: " + username);
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while adding user: " + ex);
			return false;
		}
	}

	// Function to edit an existing user
	public boolean editUser(String username, String userRole, String userStatus) {
		try {
			logger.info("Editing user with username: " + username);
			getUrl(Constants.base_url + Constants.user_url);

			// Searching for the user
			if (searchUser(username)) {
				WebElement row = driver.findElement(
						By.xpath("//table//tbody//tr//td[contains(text(),'" + username + "')]/../td[4]/a"));
				clickElement(row, "Edit Button");

				selectFromDropdown(userMasterPageLocators.userRole, userRole, "User Role");
				selectFromDropdown(userMasterPageLocators.userStatus, userStatus, "User Status");

				if (userMasterPageLocators.btnSubmit.isEnabled()) {
					clickElement(userMasterPageLocators.btnSubmit, "Submit Button");
					getTest().get().pass("User updated successfully");
					logger.info("User updated successfully: " + username);
					return true;
				} else {
					clickElement(userMasterPageLocators.btnCancel, "Cancel Button");
					getTest().get().error("Failed to update user");
					logger.error("Failed to update user: " + username);
					return false;
				}
			} else {
				getTest().get().fail("User not found for username: " + username);
				logger.error("User not found: " + username);
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while editing user: " + ex);
			return false;
		}
	}

	// Function to export the data to Excel
	public boolean exportExcel() {
		try {
			logger.info("Exporting user data to Excel");
			clickElement(userMasterPageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded Successfully");
			return true;
		} catch (Exception ex) {
			logger.error("Error while exporting to Excel: " + ex.getMessage());
			getTest().get().fail("An error occurred while exporting to Excel: " + ex.getMessage());
			return false;
		}
	}
}
