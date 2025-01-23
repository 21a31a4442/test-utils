package com.pvs.admin.department.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pvs.admin.department.pagefunctions.DeptDataProvider;
import com.pvs.admin.department.pagefunctions.DeptPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class DeptPageTest {
	ExtentTestNGITestListener extent;

	WebDriver driver;
	public DeptPageFunctions deptfunctions;
	public CommonActions commonactions;

	public DeptPageTest() {
		driver = WebDriverUtil.getDriver();

		deptfunctions = new DeptPageFunctions(driver);
		commonactions = new CommonActions(driver);

	}

	@Test(priority = 0, dataProvider = "addDept", dataProviderClass = DeptDataProvider.class)
	public void addDept(String deptName, String deptCode, String status) {
		ExtentTestNGITestListener.createNode("Add Department");
		getTest().get().info("Adding Department");
		deptfunctions.addDept(deptName, deptCode, status);
	}

	@Test(priority = 1, dataProvider = "editDept", dataProviderClass = DeptDataProvider.class)
	public void editDept(String searchDept, String searchCode, String deptName, String deptCode, String status) {

		ExtentTestNGITestListener.createNode("Edit Department");
		getTest().get().info("Updating Department");
		deptfunctions.editDept(searchDept, searchCode, deptName, deptCode, status);
	}

	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Department");
		getTest().get().info("Downloading Department report...");
		deptfunctions.exportExcel();
	}

}
