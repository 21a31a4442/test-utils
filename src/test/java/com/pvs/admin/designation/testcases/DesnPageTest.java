package com.pvs.admin.designation.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pvs.admin.designation.pagefunctions.DesnDataProvider;
import com.pvs.admin.designation.pagefunctions.DesnPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class DesnPageTest {
	ExtentTestNGITestListener extent;

	WebDriver driver;
	public DesnPageFunctions desnfunctions;
	public CommonActions commonactions;

	public DesnPageTest() {
		driver = WebDriverUtil.getDriver();

		desnfunctions = new DesnPageFunctions(driver);
		commonactions = new CommonActions(driver);

	}

	@Test(priority = 0, dataProvider = "addDesn", dataProviderClass = DesnDataProvider.class)
	public void addDesn(String desnName, String desnCode, String status) {
		ExtentTestNGITestListener.createNode("Add Designation");
		getTest().get().info("Adding Designation");
		desnfunctions.addDesn(desnName, desnCode, status);
	}

	@Test(priority = 1, dataProvider = "editDesn", dataProviderClass = DesnDataProvider.class)
	public void editDesn(String searchDesn, String searchCode, String desnName, String desnCode, String status) {

		ExtentTestNGITestListener.createNode("Edit Designation");
		getTest().get().info("Updating Designation");
		desnfunctions.editDesn(searchDesn, searchCode, desnName, desnCode, status);
	}

	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Designation");
		getTest().get().info("Downloading Designation report...");
		desnfunctions.exportExcel();
	}

}


