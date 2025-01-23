package com.pvs.admin.state.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.state.pageobject.StatePageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class StatePageFunctions extends CommonActions {

	private WebDriver driver;
	StatePageLocators statepagelocators;
	private static final Logger logger = LogManager.getLogger(StatePageFunctions.class);

	public StatePageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		statepagelocators = new StatePageLocators();
		PageFactory.initElements(driver, statepagelocators);
	}

	public boolean searchState(String stateName) {
		try {
			boolean res = false;
			int count = 0;
			logger.info("Searching State by name");
			getUrl(Constants.base_url + Constants.state_url);
			sendKeys(statepagelocators.search, stateName, "Searching State table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkState = match.findElement(By.xpath(".//td[1]")).getText();
				if (stateName.equalsIgnoreCase(checkState)) {
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
			logger.error("An Error occurred while searching: " + ex.getMessage());
			return false;
		}
	}

	public WebElement searchStateAndReturnRow(String stateName) {
		try {
			logger.info("Searching State by name and returning a row");
			sendKeys(statepagelocators.search, stateName, "Searching State Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String state = match.findElement(By.xpath("./td[1]")).getText();
				if (state.equalsIgnoreCase(stateName)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An Error occurred while searching and returning row: " + ex.getMessage());
			return null;
		}
	}

	public boolean addState(String stateName, String countryName) {
		try {
			logger.info("Adding State record");
			getUrl(Constants.base_url + Constants.state_url);
			clickElement(statepagelocators.addState, "Add State");

			sendKeys(statepagelocators.stateName, stateName, "State Name");
			selectFromDropdown(statepagelocators.country, countryName, "Country");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (statepagelocators.btnSubmit.isEnabled()) {
				clickElement(statepagelocators.btnSubmit, "Submit");
				getTest().get().pass("No duplicates found. State added successfully.");
				logger.info("State added successfully");
				return true;
			} else {
				clickElement(statepagelocators.btnCancel, "Cancel");
				getTest().get().error("Duplicate State found!");
				getTest().get().pass("State has not been added");
				logger.info("Duplicate State found");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while adding State record: " + ex.getMessage());
			logger.error("Exception occurred while adding State: " + ex.getMessage());
			return false;
		}
	}

	public boolean editState(String searchStateName, String newStateName, String newCountryName) {
		try {
			logger.info("Editing State");
			getUrl(Constants.base_url + Constants.state_url);
			WebElement row = searchStateAndReturnRow(searchStateName);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[3]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(statepagelocators.stateName, newStateName, "New State Name");
				selectFromDropdown(statepagelocators.country, newCountryName, "New Country");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (statepagelocators.btnSubmit.isEnabled()) {
					clickElement(statepagelocators.btnSubmit, "Submit");
					getTest().get().pass("No duplicates found. State updated successfully.");
					logger.info("State updated successfully");
					return true;
				} else {
					clickElement(statepagelocators.btnCancel, "Cancel");
					getTest().get().error("Duplicate State found");
					getTest().get().pass("State has not been updated");
					logger.info("Duplicate State found");
					return false;
				}
			} else {
				getTest().get().fail("No such State present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An exception occurred while editing State: " + ex.getMessage());
			getTest().get().fail("An exception occurred while editing State: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(statepagelocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully");
			return true;
		} catch (Exception ex) {
			logger.info("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}
}
