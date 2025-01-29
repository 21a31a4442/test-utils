package com.pvs.cms.configurations.vendor.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.vendor.pageobject.VendorPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class VendorPageFunctions extends CommonActions {

	private WebDriver driver;
	private VendorPageLocators vendorpagelocators;
	private static Logger logger = LogManager.getLogger(VendorPageFunctions.class);

	public VendorPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		vendorpagelocators = new VendorPageLocators();
		PageFactory.initElements(driver, vendorpagelocators);
	}

	// Add Vendor
	public boolean addVendor(String vendorName, String address1, String address2, String pincode, String city,
			String state, String country, String primaryContact, String status) {
		try {
			logger.info("Adding Vendor");
			getUrl(Constants.base_url + Constants.vendor_url);
			clickElement(vendorpagelocators.addVendor, "Add Vendor");

			sendKeys(vendorpagelocators.name, vendorName, "Vendor Name");
			sendKeys(vendorpagelocators.address1, address1, "Address 1");
			sendKeys(vendorpagelocators.address2, address2, "Address 2");
			sendKeys(vendorpagelocators.pincode, pincode, "Pincode");

			selectFromDropdown(vendorpagelocators.city, city, "City");
			selectFromDropdown(vendorpagelocators.state, state, "State");
			selectFromDropdown(vendorpagelocators.country, country, "Country");
			selectFromDropdown(vendorpagelocators.primContact, primaryContact, "Primary Contact");
			selectFromDropdown(vendorpagelocators.status, status, "Status");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (vendorpagelocators.btnSubmit.isEnabled()) {
				clickElement(vendorpagelocators.btnSubmit, "Submit Button");
				getTest().get().pass("Vendor added successfully");
				logger.info("Vendor added successfully");
				return true;
			} else {
				clickElement(vendorpagelocators.btnCancel, "Cancel Button");
				getTest().get().error("Error: Unable to add Vendor");
				logger.error("Error: Unable to add Vendor");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error occurred while adding vendor: " + ex.getMessage());
			return false;
		}
	}

	// Edit Vendor
	public boolean editVendor(String searchVendorName, String vendorName, String address1, String address2,
			String pincode, String city, String state, String country, String primaryContact, String status) {
		try {
			logger.info("Editing Vendor");
			getUrl(Constants.base_url + Constants.vendor_url);
			WebElement row = searchVendorAndReturnRow(searchVendorName);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[5]//a")); // Assuming 5th column has the edit button
				clickElement(editBtn, "Edit Button");

				sendKeys(vendorpagelocators.name, vendorName, "Vendor Name");
				sendKeys(vendorpagelocators.address1, address1, "Address 1");
				sendKeys(vendorpagelocators.address2, address2, "Address 2");
				sendKeys(vendorpagelocators.pincode, pincode, "Pincode");

				selectFromDropdown(vendorpagelocators.city, city, "City");
				selectFromDropdown(vendorpagelocators.state, state, "State");
				selectFromDropdown(vendorpagelocators.country, country, "Country");
				selectFromDropdown(vendorpagelocators.primContact, primaryContact, "Primary Contact");
				selectFromDropdown(vendorpagelocators.status, status, "Status");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (vendorpagelocators.btnSubmit.isEnabled()) {
					clickElement(vendorpagelocators.btnSubmit, "Submit Button");
					getTest().get().pass("Vendor updated successfully");
					logger.info("Vendor updated successfully");
					return true;
				} else {
					clickElement(vendorpagelocators.btnCancel, "Cancel Button");
					getTest().get().error("Error: Unable to update Vendor");
					logger.error("Error: Unable to update Vendor");
					return false;
				}
			} else {
				getTest().get().fail("Vendor not found for editing");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error occurred while editing vendor: " + ex.getMessage());
			return false;
		}
	}

	// Search Vendor by Vendor Name
	public WebElement searchVendorAndReturnRow(String vendorName) {
		try {
			logger.info("Searching for vendor: " + vendorName);
			sendKeys(vendorpagelocators.search, vendorName, "Search Vendor");

			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkVendorName = match.findElement(By.xpath(".//td[2]")).getText(); // Assuming name is in 2nd column
				if (vendorName.equalsIgnoreCase(checkVendorName)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error while searching vendor: " + ex.getMessage());
			return null;
		}
	}

	// Export Vendor Data to Excel
	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(vendorpagelocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded Successfully");
			return true;
		} catch (Exception ex) {
			logger.error("Error while downloading Excel file: " + ex.getMessage());
			getTest().get().fail("Error while downloading the Excel file");
			return false;
		}
	}

}
