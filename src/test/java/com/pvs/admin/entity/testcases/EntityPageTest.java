package com.pvs.admin.entity.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pvs.admin.entity.pagefunctions.EntityDataProvider;
import com.pvs.admin.entity.pagefunctions.EntityPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class EntityPageTest {

	ExtentTestNGITestListener extent;

	WebDriver driver;
	public EntityPageFunctions entityfunctions;
	public CommonActions commonactions;

	public EntityPageTest() {
		driver = WebDriverUtil.getDriver();

		entityfunctions = new EntityPageFunctions(driver);
		commonactions = new CommonActions(driver);

	}

	@Test(priority = 0, dataProvider = "addEntity", dataProviderClass = EntityDataProvider.class)
	public void addEntity(String entityName, String ccCode, String cinNo, String incorpDate, String gstNum,
			String address1, String address2, String city, String state, String country, String pincode,
			String primContact, String status) {
		
		ExtentTestNGITestListener.createNode("Add Entity");
		getTest().get().info("Adding Entity");
		entityfunctions.addEntity(entityName, ccCode, cinNo, incorpDate, gstNum, address1, address2, city, state, country, pincode, primContact, status);
		entityfunctions.verifyEntityHQ(entityName);
	}

	@Test(priority = 1, dataProvider = "editEntity", dataProviderClass = EntityDataProvider.class)
	public void editEntity(String searchEntityName, String newEntityName, String newCinNo, String newIncorpDate,
			String newStatus) {

		ExtentTestNGITestListener.createNode("Edit Entity");
		getTest().get().info("Updating Entity");
		entityfunctions.editEntity(searchEntityName, newEntityName, newCinNo, newIncorpDate, newStatus);
		
	}

	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Entity");
		getTest().get().info("Downloading Entity report...");
		entityfunctions.exportExcel();
	}


	
	
}
