package com.pvs.cms.configurations.lessor.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.lessor.pageobject.LessorPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class LessorPageFunctions extends CommonActions {

	private WebDriver driver;
	private LessorPageLocators lessorPageLocators;
	private static Logger logger = LogManager.getLogger(LessorPageFunctions.class);

	public LessorPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		lessorPageLocators = new LessorPageLocators();
		PageFactory.initElements(driver, lessorPageLocators);
	}

	public boolean searchLessor(String lessorName) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.lessor_url);
			logger.info("Searching for lessor with name: " + lessorName);
			sendKeys(lessorPageLocators.search, lessorName, "Searching lessor table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkName = match.findElement(By.xpath(".//td[2]")).getText();
				if (lessorName.equalsIgnoreCase(checkName)) {
					res = true;
					count += 1;
				}
			}
			if (res) {
				return true;
			} else if (count > 1) {
				return false;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching for lessor: " + ex);
			return false;
		}
	}

	public WebElement searchLessorAndReturnRow(String lessorName) {
		try {
			getUrl(Constants.base_url + Constants.lessor_url);
			logger.info("Searching and returning row for lessor with name: " + lessorName);
			sendKeys(lessorPageLocators.search, lessorName, "Searching lessor table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkName = match.findElement(By.xpath(".//td[2]")).getText();
				if (lessorName.equalsIgnoreCase(checkName)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching and returning row for lessor: " + ex);
			return null;
		}
	}

	public boolean addLessor(String lessorName, String lessorType, String address1, String address2, String pincode,
			String city, String state, String country, String primaryContact, String status) {
		try {
			logger.info("Adding lessor record");
			getUrl(Constants.base_url + Constants.lessor_url);
			clickElement(lessorPageLocators.addLessor, "Add Lessor");

			sendKeys(lessorPageLocators.name, lessorName, "Lessor Name");
			selectFromDropdown(lessorPageLocators.lessorType, lessorType, "Lessor Type");
			sendKeys(lessorPageLocators.address1, address1, "Address 1");
			sendKeys(lessorPageLocators.address2, address2, "Address 2");
			sendKeys(lessorPageLocators.pincode, pincode, "Pincode");
			selectFromDropdown(lessorPageLocators.city, city, "City");
			selectFromDropdown(lessorPageLocators.state, state, "State");
			selectFromDropdown(lessorPageLocators.country, country, "Country");
			selectFromDropdown(lessorPageLocators.primContact, primaryContact, "Primary Contact");
			selectFromDropdown(lessorPageLocators.status, status, "Status");

			if (lessorPageLocators.btnSubmit.isEnabled()) {
				clickElement(lessorPageLocators.btnSubmit, "Submit Button");
				getTest().get().pass("Lessor added successfully");
				logger.info("Lessor added successfully");
				return true;
			} else {
				clickElement(lessorPageLocators.btnCancel, "Cancel Button");
				getTest().get().error("Failed to add lessor due to validation errors");
				logger.error("Failed to add lessor");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while adding lessor: " + ex);
			return false;
		}
	}

	public boolean editLessor(String searchLessorName, String lessorName, String lessorType, String address1,
			String address2, String pincode, String city, String state, String country, String primaryContact,
			String status) {
		try {
			logger.info("Editing lessor record");
			getUrl(Constants.base_url + Constants.lessor_url);
			WebElement row = searchLessorAndReturnRow(searchLessorName);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(lessorPageLocators.name, lessorName, "Lessor Name");
				selectFromDropdown(lessorPageLocators.lessorType, lessorType, "Lessor Type");
				sendKeys(lessorPageLocators.address1, address1, "Address 1");
				sendKeys(lessorPageLocators.address2, address2, "Address 2");
				sendKeys(lessorPageLocators.pincode, pincode, "Pincode");
				selectFromDropdown(lessorPageLocators.city, city, "City");
				selectFromDropdown(lessorPageLocators.state, state, "State");
				selectFromDropdown(lessorPageLocators.country, country, "Country");
				selectFromDropdown(lessorPageLocators.primContact, primaryContact, "Primary Contact");
				selectFromDropdown(lessorPageLocators.status, status, "Status");

				if (lessorPageLocators.btnSubmit.isEnabled()) {
					clickElement(lessorPageLocators.btnSubmit, "Submit Button");
					getTest().get().pass("Lessor updated successfully");
					logger.info("Lessor updated successfully");
					return true;
				} else {
					clickElement(lessorPageLocators.btnCancel, "Cancel Button");
					getTest().get().error("Failed to update lessor due to validation errors");
					logger.error("Failed to update lessor");
					return false;
				}
			} else {
				getTest().get().fail("Lessor not found for editing");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while editing lessor: " + ex);
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(lessorPageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded Successfully");
			return true;
		} catch (Exception ex) {
			logger.error("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}
}
