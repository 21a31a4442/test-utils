package com.pvs.admin.entity.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.entity.pageobject.EntityPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class EntityPageFunctions extends CommonActions {

	private WebDriver driver;
	EntityPageLocators entityPageLocators;
	private static final Logger logger = LogManager.getLogger(EntityPageFunctions.class);

	public EntityPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		entityPageLocators = new EntityPageLocators();
		PageFactory.initElements(driver, entityPageLocators);
	}

	public boolean searchEntity(String entityName) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.entity_url);
			sendKeys(entityPageLocators.search, entityName, "Searching Entity table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkEntityName = match.findElement(By.xpath(".//td[1]")).getText();
				if (entityName.equalsIgnoreCase(checkEntityName)) {
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

	public WebElement searchEntityAndReturnRow(String entityName) {
		try {
			sendKeys(entityPageLocators.search, entityName, "Searching Entity Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String name = match.findElement(By.xpath("./td[1]")).getText();
				if (name.equalsIgnoreCase(entityName)) {
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

	public boolean addEntity(String entityName, String ccCode, String cinNo, String incorpDate, String gstNum,
			String address1, String address2, String city, String state, String country, String pincode,
			String primContact, String status) {
		try {
			getUrl(Constants.base_url + Constants.entity_url);
			clickElement(entityPageLocators.addEntity, "Add Entity");

			sendKeys(entityPageLocators.entityName, entityName, "Entity Name");
			sendKeys(entityPageLocators.ccCode, ccCode, "Cost Center Code (HQ)");
			sendKeys(entityPageLocators.cinNo, cinNo, "CIN Number");
			sendKeys(entityPageLocators.incorpDate, incorpDate, "Incorporation Date");
			sendKeys(entityPageLocators.gstNum, gstNum, "GST Number");
			sendKeys(entityPageLocators.address1, address1, "Address 1");
			sendKeys(entityPageLocators.address2, address2, "Address 2");
			sendKeys(entityPageLocators.pincode, pincode, "Pincode");

			selectFromDropdown(entityPageLocators.city, city, "City");
			selectFromDropdown(entityPageLocators.state, state, "State");
			selectFromDropdown(entityPageLocators.country, country, "Country");
			selectFromDropdown(entityPageLocators.primContact, primContact, "Primary Contact");
			selectFromDropdown(entityPageLocators.status, status, "Status");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (entityPageLocators.btnSubmit.isEnabled()) {
				clickElement(entityPageLocators.btnSubmit, "Submit");
				getTest().get().pass("Entity added successfully.");
				return true;
			} else {
				clickElement(entityPageLocators.btnCancel, "Cancel");
				getTest().get().error("Failed to add entity due to duplicate or validation error.");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while adding Entity: " + ex.getMessage());
			return false;
		}
	}

	public boolean editEntity(String searchEntityName, String newEntityName, String newCinNo, String newIncorpDate,
			String newStatus) {
		try {
			getUrl(Constants.base_url + Constants.entity_url);
			WebElement row = searchEntityAndReturnRow(searchEntityName);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(entityPageLocators.entityName, newEntityName, "New Entity Name");
				sendKeys(entityPageLocators.cinNo, newCinNo, "New CIN Number");
				sendKeys(entityPageLocators.incorpDate, newIncorpDate, "New Incorporation Date");
				selectFromDropdown(entityPageLocators.status, newStatus, "New Status");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (entityPageLocators.btnSubmit.isEnabled()) {
					clickElement(entityPageLocators.btnSubmit, "Submit");
					getTest().get().pass("Entity updated successfully.");
					return true;
				} else {
					clickElement(entityPageLocators.btnCancel, "Cancel");
					getTest().get().error("Failed to edit entity due to validation error.");
					return false;
				}
			} else {
				getTest().get().fail("No such Entity present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while editing Entity: " + ex.getMessage());
			return false;
		}
	}

	public boolean verifyEntityHQ(String entityName) {
		try {
			logger.info("Verificaton of entity in cost center page");
			getTest().get().info("Verification of Entity added as Head Quarter in Cost Center");
			getUrl(Constants.base_url + Constants.costcenter_url);
			sendKeys(entityPageLocators.search, entityName + " Head-Office", "Searching Cost Center Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String entity = match.findElement(By.xpath(".//td[1]")).getText();
				if (entity.equals(entityName)) {
					logger.info("Entity added as head quarter in cost center");
					getTest().get().pass("Entity added as Head Quarter in Cost Center");
					return true;
				} else {
					logger.error("Entity has not been added as head quarters in cost center");
					getTest().get().fail("Entity has not been added as Head Quarter in Cost Center");
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An Exception Occured while verifying entity in cost center: " + ex.getMessage());
			logger.error("An Exception occured while verifying cost center" + ex.getMessage());
			return false;
		}
		return false;
	}

	public boolean exportExcel() {
		try {
			clickElement(entityPageLocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully.");
			return true;
		} catch (Exception ex) {
			getTest().get().fail("An exception occurred while downloading the Excel file: " + ex.getMessage());
			return false;
		}
	}

}
