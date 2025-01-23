package com.pvs.admin.designation.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.designation.pageobject.DesnPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class DesnPageFunctions extends CommonActions {

	private WebDriver driver;
	DesnPageLocators desnPageLocators;
	private static final Logger logger = LogManager.getLogger(DesnPageFunctions.class);

	public DesnPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		desnPageLocators = new DesnPageLocators();
		PageFactory.initElements(driver, desnPageLocators);
	}

	public boolean searchDesn(String desnName, String desnCode) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.desn_url);
			sendKeys(desnPageLocators.search, desnName + " " + desnCode, "Searching Designation table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkDesnName = match.findElement(By.xpath(".//td[1]")).getText();
				String checkDesnCode = match.findElement(By.xpath(".//td[2]")).getText();
				if (desnName.equalsIgnoreCase(checkDesnName) && desnCode.equalsIgnoreCase(checkDesnCode)) {
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
			logger.error("An error occurred while searching: " + ex.getMessage());
			return false;
		}
	}

	public WebElement searchDesnAndReturnRow(String desnName, String desnCode) {
		try {
			sendKeys(desnPageLocators.search, desnName + " " + desnCode, "Searching Designation Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String name = match.findElement(By.xpath("./td[1]")).getText();
				String code = match.findElement(By.xpath(".//td[2]")).getText();
				if (name.equalsIgnoreCase(desnName) && code.equalsIgnoreCase(desnCode)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An error occurred while searching and returning row: " + ex.getMessage());
			return null;
		}
	}

	public boolean addDesn(String desnName, String desnCode, String status) {
		try {
			getUrl(Constants.base_url + Constants.desn_url);
			clickElement(desnPageLocators.addDesn, "Add Designation");

			sendKeys(desnPageLocators.desnName, desnName, "Designation Name");
			sendKeys(desnPageLocators.desnCode, desnCode, "Designation Code");
			selectFromDropdown(desnPageLocators.status, status, "Status");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (desnPageLocators.btnSubmit.isEnabled()) {
				clickElement(desnPageLocators.btnSubmit, "Submit");
				getTest().get().pass("No duplicates found. Designation added successfully.");
				return true;
			} else {
				clickElement(desnPageLocators.btnCancel, "Cancel");
				getTest().get().error("Duplicate Designation found!");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while adding Designation: " + ex.getMessage());
			return false;
		}
	}

	public boolean editDesn(String searchDesnName, String searchDesnCode, String newDesnName, String newDesnCode,
			String newStatus) {
		try {
			getUrl(Constants.base_url + Constants.desn_url);
			WebElement row = searchDesnAndReturnRow(searchDesnName, searchDesnCode);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(desnPageLocators.desnName, newDesnName, "New Designation Name");
				sendKeys(desnPageLocators.desnCode, newDesnCode, "New Designation Code");
				selectFromDropdown(desnPageLocators.status, newStatus, "New Status");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (desnPageLocators.btnSubmit.isEnabled()) {
					clickElement(desnPageLocators.btnSubmit, "Submit");
					getTest().get().pass("No duplicates found. Designation updated successfully.");
					return true;
				} else {
					clickElement(desnPageLocators.btnCancel, "Cancel");
					getTest().get().error("Duplicate Designation found");
					return false;
				}
			} else {
				getTest().get().fail("No such Designation present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while editing Designation: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			clickElement(desnPageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully");
			return true;
		} catch (Exception ex) {
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}
}
