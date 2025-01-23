package com.pvs.admin.costcenter.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pvs.admin.costcenter.pagefunctions.CostCenterDataProvider;
import com.pvs.admin.costcenter.pagefunctions.CostCenterPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class CostCenterPageTest {
	
	ExtentTestNGITestListener extent;

	WebDriver driver;
	public CostCenterPageFunctions costcenterfunctions;
	public CommonActions commonactions;

	public CostCenterPageTest() {
		driver = WebDriverUtil.getDriver();

		costcenterfunctions = new CostCenterPageFunctions(driver);
		commonactions = new CommonActions(driver);

	}

	@Test(priority = 0, dataProvider = "addCostCenter", dataProviderClass = CostCenterDataProvider.class)
	public void addCostCenter(String ccCode, String entityParent, String incorpDate, String gstNum, 
            String address1, String address2, String city, String state, String country, 
            String pincode, String primContact, String status) {

		ExtentTestNGITestListener.createNode("Add Cost Center");
		getTest().get().info("Adding Cost Center");
		costcenterfunctions.addCostCenter(ccCode, entityParent, incorpDate, gstNum, address1, address2, city, state, country, pincode, primContact, status);
	}

	@Test(priority = 1, dataProvider = "editCostCenter", dataProviderClass = CostCenterDataProvider.class)
	public void editCostCenter(String searchCCCode, String newCCCode, String newEntityParent, String newIncorpDate, 
            String newGstNum, String newAddress1, String newAddress2, String newCity, 
            String newState, String newCountry, String newPincode, String newPrimContact, 
            String newStatus) {

		ExtentTestNGITestListener.createNode("Edit Cost Center");
		getTest().get().info("Updating Cost Center");
		costcenterfunctions.editCostCenter(searchCCCode, newCCCode, newEntityParent, newIncorpDate, newGstNum, newAddress1, newAddress2, newCity, newState, newCountry, newPincode, newPrimContact, newStatus);
	}

	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Cost Center");
		getTest().get().info("Downloading Cost Center report...");
		costcenterfunctions.exportExcel();
	}
}
