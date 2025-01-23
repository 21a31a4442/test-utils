package com.pvs.admin.status.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.admin.status.pageobject.StatusPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class StatusPageFunctions extends CommonActions {

	private WebDriver driver;
	private StatusPageLocators statuspagelocators;
	private static Logger logger = LogManager.getLogger(StatusPageFunctions.class);

	public StatusPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		statuspagelocators = new StatusPageLocators();
		PageFactory.initElements(driver, statuspagelocators);
	}

	public boolean searchStatus(String name, String module) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.status_url);
			logger.info("searching the status name and status module");
			sendKeys(statuspagelocators.search, name + " " + module, "Searching status table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkStatus = match.findElement(By.xpath(".//td[2]")).getText();
				String checkModule = match.findElement(By.xpath(".//td[3]")).getText();
				if (name.equalsIgnoreCase(checkStatus) && module.equalsIgnoreCase(checkModule)) {
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
			logger.error("Error while searching the value in the status page: " + ex);
			return false;
		}

	}

	public WebElement searchStatusAndReturnRow(String name, String module) {
		try {
//			getUrl(Constants.base_url + Constants.status_url);
			logger.info("searching the status name and status module and return the row");
			sendKeys(statuspagelocators.search, name + " " + module, "Searching status table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkStatus = match.findElement(By.xpath(".//td[2]")).getText();
				String checkModule = match.findElement(By.xpath(".//td[3]")).getText();
				if (name.equalsIgnoreCase(checkStatus) && module.equalsIgnoreCase(checkModule)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching and return the row in the status page: " + ex);
			return null;
		}

	}

	public boolean addStatus(String statusCode, String statusName, String statusModule) {
		try {
			logger.info("Adding status Record");
//			ExtentTestNGITestListener.createNode("Add Status");
//			getTest().get().info("Adding Status");
			getUrl(Constants.base_url + Constants.status_url);
			clickElement(statuspagelocators.addStatus, "Add Status");

			sendKeys(statuspagelocators.statusCode, statusCode, "Status Code");
			sendKeys(statuspagelocators.statusName, statusName, "Status Name");
			sendKeys(statuspagelocators.statusModule, statusModule, "Status Module");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (statuspagelocators.btnSubmit.isEnabled()) {
				clickElement(statuspagelocators.btnSubmit, "Submit Button");
				getTest().get().pass("No duplicates found status added successfully");
				logger.info("No duplicates found Status added successfully");
				return true;
			} else {
//				driver.navigate().back();
				clickElement(statuspagelocators.btnCancel, "Cancel Button");
				getTest().get().error("Duplicate status found!");
				getTest().get().pass("status has been not added");
				logger.info("Duplicate Status found!");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error occured while adding a duplicate status: " + ex);
			return false;
		}
	}

	public boolean editStatus(String searchStatus, String searchModule, String statusCode, String statusName,
			String statusModule) {
		try {
			logger.info("Editing status");
			getUrl(Constants.base_url + Constants.status_url);
			WebElement row = searchStatusAndReturnRow(searchStatus, searchModule);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");
				sendKeys(statuspagelocators.statusCode, statusCode, "Status Code");
				sendKeys(statuspagelocators.statusName, statusName, "Status Name");
				sendKeys(statuspagelocators.statusModule, statusModule, "Status Module");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (statuspagelocators.btnSubmit.isEnabled()) {
					clickElement(statuspagelocators.btnSubmit, "Submit");
					getTest().get().pass("No duplicates found Status Updated Successfully");
					logger.info("Status added successfully");
					return true;
				} else {
					clickElement(statuspagelocators.btnCancel, "Cancel");
					getTest().get().error("Duplicate Status found");
					getTest().get().pass("Status has not been added");
					logger.info("Duplicate Status found");
					return false;
				}

			} else {
				getTest().get().fail("No such Status present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An exception occured while editing status: " + ex.getMessage());
			getTest().get().fail("An Exception occured while editing Status: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(statuspagelocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded Successfully");
			return true;
		} catch (Exception ex) {
			logger.info("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occured while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}

}
