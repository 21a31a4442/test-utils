package com.pvs.cms.configurations.warehouse.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.warehouse.pageobject.WarehousePageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class WarehousePageFunctions extends CommonActions {

	private WebDriver driver;
	private WarehousePageLocators warehousePageLocators;
	private static Logger logger = LogManager.getLogger(WarehousePageFunctions.class);

	public WarehousePageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		warehousePageLocators = new WarehousePageLocators();
		PageFactory.initElements(driver, warehousePageLocators);
	}

	// Search for warehouse by warehouse name and warehouse code
	public boolean searchWarehouse(String warehouseName, String warehouseCode) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.warehouse_url);
			logger.info("Searching warehouse by name and code");

			sendKeys(warehousePageLocators.search, warehouseName + " " + warehouseCode, "Searching warehouse table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkName = match.findElement(By.xpath(".//td[2]")).getText();
				String checkCode = match.findElement(By.xpath(".//td[3]")).getText();
				if (warehouseName.equalsIgnoreCase(checkName) && warehouseCode.equalsIgnoreCase(checkCode)) {
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
			logger.error("Error while searching the value in the warehouse page: " + ex);
			return false;
		}
	}

	// Add new warehouse record
	public boolean addWarehouse(String warehouseName, String warehouseCode, String address1, String address2,
			String pincode, String capacitySqft, String lessor, String city, String state, String country,
			String primaryContact, String status) {
		try {
			logger.info("Adding warehouse record");
			getUrl(Constants.base_url + Constants.warehouse_url);
			clickElement(warehousePageLocators.addWarehouse, "Add Warehouse");

			sendKeys(warehousePageLocators.whouseName, warehouseName, "Warehouse Name");
			sendKeys(warehousePageLocators.whouseCode, warehouseCode, "Warehouse Code");
			sendKeys(warehousePageLocators.address1, address1, "Address 1");
			sendKeys(warehousePageLocators.address2, address2, "Address 2");
			sendKeys(warehousePageLocators.pincode, pincode, "Pincode");
			sendKeys(warehousePageLocators.capacitySqft, capacitySqft, "Capacity (Sq.ft.)");

			triggerErrormsg();
			
			if (Math.abs((Double.parseDouble(capacitySqft) / 4)
					- (Double.parseDouble(warehousePageLocators.capacityMton.getText()))) > 0.01) {
				getTest().get().error("Capacity (mton) not calculated correctly");
				logger.error("Capacity (mton) calculation failed. Expected:");
				return false;
			}
			
			
			selectFromDropdown(warehousePageLocators.lessor, lessor, "Lessor");
			selectFromDropdown(warehousePageLocators.city, city, "City");
			selectFromDropdown(warehousePageLocators.state, state, "State");
			selectFromDropdown(warehousePageLocators.country, country, "Country");
			selectFromDropdown(warehousePageLocators.primContact, primaryContact, "Primary Contact");
			selectFromDropdown(warehousePageLocators.status, status, "Status");

			if (warehousePageLocators.btnSubmit.isEnabled()) {
				clickElement(warehousePageLocators.btnSubmit, "Submit Button");
				getTest().get().pass("Warehouse added successfully");
				logger.info("Warehouse added successfully");
				return true;
			} else {
				clickElement(warehousePageLocators.btnCancel, "Cancel Button");
				getTest().get().error("Failed to add warehouse!");
				logger.error("Failed to add warehouse");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error occurred while adding warehouse: " + ex);
			return false;
		}
	}

	// Edit warehouse record
	public boolean editWarehouse(String warehouseName, String warehouseCode, String warehouseNewName,
			String warehouseNewCode, String address1, String address2, String pincode, String capacitySqft,
			String lessor, String city, String state, String country, String primaryContact, String status) {
		try {
			logger.info("Editing warehouse record");
			getUrl(Constants.base_url + Constants.warehouse_url);
			WebElement row = searchWarehouseAndReturnRow(warehouseName, warehouseCode);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[6]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(warehousePageLocators.whouseName, warehouseNewName, "Warehouse Name");
				sendKeys(warehousePageLocators.whouseCode, warehouseNewCode, "Warehouse Code");
				sendKeys(warehousePageLocators.address1, address1, "Address 1");
				sendKeys(warehousePageLocators.address2, address2, "Address 2");
				sendKeys(warehousePageLocators.pincode, pincode, "Pincode");
				sendKeys(warehousePageLocators.capacitySqft, capacitySqft, "Capacity (Sq.ft.)");

				selectFromDropdown(warehousePageLocators.lessor, lessor, "Lessor");
				selectFromDropdown(warehousePageLocators.city, city, "City");
				selectFromDropdown(warehousePageLocators.state, state, "State");
				selectFromDropdown(warehousePageLocators.country, country, "Country");
				selectFromDropdown(warehousePageLocators.primContact, primaryContact, "Primary Contact");
				selectFromDropdown(warehousePageLocators.status, status, "Status");

				if (Math.abs((Double.parseDouble(capacitySqft) / 4)
						- (Double.parseDouble(warehousePageLocators.capacityMton.getText()))) > 0.01) {
					getTest().get().error("Capacity (mton) not calculated correctly");
					logger.error("Capacity (mton) calculation failed. Expected:");
					return false;
				}

				if (warehousePageLocators.btnSubmit.isEnabled()) {
					clickElement(warehousePageLocators.btnSubmit, "Submit");
					getTest().get().pass("Warehouse updated successfully");
					logger.info("Warehouse updated successfully");
					return true;
				} else {
					clickElement(warehousePageLocators.btnCancel, "Cancel");
					getTest().get().error("Failed to update warehouse!");
					logger.error("Failed to update warehouse");
					return false;
				}

			} else {
				getTest().get().fail("No such warehouse found");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An exception occurred while editing warehouse: " + ex.getMessage());
			getTest().get().fail("An exception occurred while editing warehouse: " + ex.getMessage());
			return false;
		}
	}

	// Export warehouse details to Excel
	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(warehousePageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel file downloaded successfully");
			return true;
		} catch (Exception ex) {
			logger.error("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}

	// Helper method to search and return the row of warehouse
	private WebElement searchWarehouseAndReturnRow(String warehouseName, String warehouseCode) {
		try {
			sendKeys(warehousePageLocators.search, warehouseName + " " + warehouseCode, "Searching warehouse table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkName = match.findElement(By.xpath(".//td[2]")).getText();
				String checkCode = match.findElement(By.xpath(".//td[3]")).getText();
				if (warehouseName.equalsIgnoreCase(checkName) && warehouseCode.equalsIgnoreCase(checkCode)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching and returning the warehouse row: " + ex);
			return null;
		}
	}
}
