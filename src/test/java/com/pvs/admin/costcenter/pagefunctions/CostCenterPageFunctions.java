package com.pvs.admin.costcenter.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.costcenter.pageobject.CostCenterPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class CostCenterPageFunctions extends CommonActions {

	private WebDriver driver;
	CostCenterPageLocators costCenterPageLocators;
	private static final Logger logger = LogManager.getLogger(CostCenterPageFunctions.class);

	public CostCenterPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		costCenterPageLocators = new CostCenterPageLocators();
		PageFactory.initElements(driver, costCenterPageLocators);
	}

	public boolean searchCostCenter(String ccCode) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.costcenter_url);
			sendKeys(costCenterPageLocators.search, ccCode, "Searching Cost Center table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkCCCode = match.findElement(By.xpath(".//td[1]")).getText();
				if (ccCode.equalsIgnoreCase(checkCCCode)) {
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

	public WebElement searchCostCenterAndReturnRow(String ccCode) {
		try {
			sendKeys(costCenterPageLocators.search, ccCode, "Searching Cost Center Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String code = match.findElement(By.xpath("./td[1]")).getText();
				if (code.equalsIgnoreCase(ccCode)) {
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

	public boolean addCostCenter(String ccCode, String entityParent, String incorpDate, String gstNum, String address1,
			String address2, String city, String state, String country, String pincode, String primContact,
			String status) {
		try {
			getUrl(Constants.base_url + Constants.costcenter_url);
			clickElement(costCenterPageLocators.addCostcenter, "Add Cost Center");

			sendKeys(costCenterPageLocators.ccCode, ccCode, "Cost Center Code");
			sendKeys(costCenterPageLocators.entityParent, entityParent, "Entity Parent");
			sendKeys(costCenterPageLocators.incorpDate, incorpDate, "Incorporation Date");
			sendKeys(costCenterPageLocators.gstNum, gstNum, "GST Number");
			sendKeys(costCenterPageLocators.address1, address1, "Address 1");
			sendKeys(costCenterPageLocators.address2, address2, "Address 2");
			sendKeys(costCenterPageLocators.pincode, pincode, "Pincode");

			selectFromDropdown(costCenterPageLocators.city, city, "City");
			selectFromDropdown(costCenterPageLocators.state, state, "State");
			selectFromDropdown(costCenterPageLocators.country, country, "Country");
			selectFromDropdown(costCenterPageLocators.primContact, primContact, "Primary Contact");
			selectFromDropdown(costCenterPageLocators.status, status, "Status");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (costCenterPageLocators.btnSubmit.isEnabled()) {
				clickElement(costCenterPageLocators.btnSubmit, "Submit");
				getTest().get().pass("Cost Center added successfully.");
				return true;
			} else {
				clickElement(costCenterPageLocators.btnCancel, "Cancel");
				getTest().get().error("Failed to add Cost Center due to validation error.");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while adding Cost Center: " + ex.getMessage());
			return false;
		}
	}

	public boolean editCostCenter(String searchCCCode, String newCCCode, String newEntityParent, String newIncorpDate,
			String newGstNum, String newAddress1, String newAddress2, String newCity, String newState,
			String newCountry, String newPincode, String newPrimContact, String newStatus) {
		try {
			getUrl(Constants.base_url + Constants.costcenter_url);
			WebElement row = searchCostCenterAndReturnRow(searchCCCode);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(costCenterPageLocators.ccCode, newCCCode, "New Cost Center Code");
				sendKeys(costCenterPageLocators.entityParent, newEntityParent, "New Entity Parent");
				sendKeys(costCenterPageLocators.incorpDate, newIncorpDate, "New Incorporation Date");
				sendKeys(costCenterPageLocators.gstNum, newGstNum, "New GST Number");
				sendKeys(costCenterPageLocators.address1, newAddress1, "New Address 1");
				sendKeys(costCenterPageLocators.address2, newAddress2, "New Address 2");
				sendKeys(costCenterPageLocators.pincode, newPincode, "New Pincode");

				selectFromDropdown(costCenterPageLocators.city, newCity, "New City");
				selectFromDropdown(costCenterPageLocators.state, newState, "New State");
				selectFromDropdown(costCenterPageLocators.country, newCountry, "New Country");
				selectFromDropdown(costCenterPageLocators.primContact, newPrimContact, "New Primary Contact");
				selectFromDropdown(costCenterPageLocators.status, newStatus, "New Status");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (costCenterPageLocators.btnSubmit.isEnabled()) {
					clickElement(costCenterPageLocators.btnSubmit, "Submit");
					getTest().get().pass("Cost Center updated successfully.");
					return true;
				} else {
					clickElement(costCenterPageLocators.btnCancel, "Cancel");
					getTest().get().error("Failed to edit Cost Center due to validation error.");
					return false;
				}
			} else {
				getTest().get().fail("No such Cost Center present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while editing Cost Center: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			clickElement(costCenterPageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully.");
			return true;
		} catch (Exception ex) {
			getTest().get().fail("An exception occurred while downloading the Excel file: " + ex.getMessage());
			return false;
		}
	}
}
