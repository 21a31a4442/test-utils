package com.pvs.admin.ctype.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.ctype.pageobject.ContactTypePageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class ContactTypePageFunctions extends CommonActions {

	private WebDriver driver;
	ContactTypePageLocators ctypepagelocators;
	private static final Logger logger = LogManager.getLogger(ContactTypePageFunctions.class);

	public ContactTypePageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		ctypepagelocators = new ContactTypePageLocators();
		PageFactory.initElements(driver, ctypepagelocators);
	}

	public boolean searchCtype(String ctype, String status) {
		try {
			boolean res = false;
			int count = 0;
			logger.info("searching contact type and status");
			getUrl(Constants.base_url + Constants.contact_type_url);
			sendKeys(ctypepagelocators.search, ctype + ' ' + status, "Searching Contact Type table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkCtype = match.findElement(By.xpath(".//td[1]")).getText();
				String checkStatus = match.findElement(By.xpath(".//td[2]")).getText();
				if (ctype.equalsIgnoreCase(checkCtype) && status.equalsIgnoreCase(checkStatus)) {
					res = true;
					count += 1;
				}
			}
			if (res) {
				return true;
			} else if (count > 1) {
				return false;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An Error occured while searching: " + ex.getMessage());
			return false;
		}
	}

	public WebElement searchCtypeAndReturnRow(String searchCtype, String searchStatus) {
		try {
			logger.info("searching contact type and returning a row");
			sendKeys(ctypepagelocators.search, searchCtype + " " + searchStatus, "Searching Contact Type Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String ctype = match.findElement(By.xpath(".//td[1]")).getText();
				System.out.println(ctype);
				String status = match.findElement(By.xpath(".//td[2]")).getText();
				System.out.println(status);

				System.out.println();
				System.out.println(searchCtype);
				System.out.println(searchStatus);
				if ((ctype.equals(searchCtype)) && (status.equals(searchStatus))) {
					System.out.println(ctype);
					System.out.println("Hii");
					return match;
				}
				System.out.println("Not match");
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An Error occured while searching and returning row: " + ex.getMessage());
			return null;
		}
	}

	public boolean addCtype(String name, String status) {
		try {
			logger.info("Adding Contact type record");
			getUrl(Constants.base_url + Constants.contact_type_url);
			clickElement(ctypepagelocators.addCtype, "Add Contact Type");

			sendKeys(ctypepagelocators.ctypeName, name, "Contact Type Name");
			selectFromDropdown(ctypepagelocators.status, status, "Status");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (ctypepagelocators.btnSubmit.isEnabled()) {
				clickElement(ctypepagelocators.btnSubmit, "Submit");
				getTest().get().pass("No duplicates found Contact Type Added successfully");
				logger.info("Contact Type added successfully");
				return true;
			} else {
				clickElement(ctypepagelocators.btnCancel, "Cancel");
				getTest().get().error("Duplicate Contact Type found!");
				getTest().get().pass("Contact Type has not been added");
				logger.info("Duplicate contact Type found");
				return false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occured while adding contact type record: " + ex.getMessage());
			logger.error("Exception occured while adding contact type: " + ex.getMessage());
			return false;
		}
	}

	public boolean editCtype(String searchCtype, String searchStatus, String ctypeName, String status) {
		try {
			logger.info("Editing status");
			getUrl(Constants.base_url + Constants.contact_type_url);
			WebElement row = searchCtypeAndReturnRow(searchCtype, searchStatus);
			System.out.println(row);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath(".//td[3]//a"));
				clickElement(editBtn, "Edit Button");
				sendKeys(ctypepagelocators.ctypeName, ctypeName, "Contact Type Name");
				selectFromDropdown(ctypepagelocators.status, status, "Status");

//				Thread.sleep(20);
				triggerErrormsg();
//				Thread.sleep(100);

				if (ctypepagelocators.btnSubmit.isEnabled()) {
					clickElement(ctypepagelocators.btnSubmit, "Submit");
					getTest().get().pass("No duplicates found Contact Type Updated Successfully");
					logger.info("Contact Type added successfully");
					return true;
				} else {
					clickElement(ctypepagelocators.btnCancel, "Cancel");
					getTest().get().error("Duplicate Contact Type found");
					getTest().get().pass("Contact Type has not been added");
					logger.info("Duplicate contact Type found");
					return false;
				}

			} else {
				getTest().get().fail("No such Contact Type present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An exception occured while editing contact type: " + ex.getMessage());
			getTest().get().fail("An Exception occured while editing contact type: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(ctypepagelocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully");
			return true;
		} catch (Exception ex) {
			logger.info("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occured while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}

}
