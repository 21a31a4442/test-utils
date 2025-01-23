package com.pvs.admin.cctype.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import com.pvs.admin.cctype.pagefunctions.CcTypeDataProvider;
import com.pvs.admin.cctype.pagefunctions.CcTypePageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class CcTypeTest {

	ExtentTestNGITestListener extent;
	WebDriver driver;
	CcTypePageFunctions cctypefunctions;
	CommonActions commonactions;
	
	public CcTypeTest() {
		driver = WebDriverUtil.getDriver();
		cctypefunctions = new CcTypePageFunctions(driver);
		commonactions = new CommonActions(driver);
	}
	
	
	@Test(priority = 0, dataProvider = "addCcType",dataProviderClass = CcTypeDataProvider.class)
	public void addCostcenterType(String cctype) {
		ExtentTestNGITestListener.createNode("Add Cost Center Type");
		getTest().get().info("Adding Cost Center Type");
		cctypefunctions.addCctype(cctype);  
	} 
	
	@Test(priority = 1,dataProvider = "editCcType",dataProviderClass = CcTypeDataProvider.class)
	public void editCostcenterType(String searchCctype, String cctype) {
		ExtentTestNGITestListener.createNode("Edit Cost Center Type");
		getTest().get().info("Updating Cost Center Type");
		cctypefunctions.editCctype(searchCctype, cctype);
	}
	
	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Cost Center Type");
		getTest().get().info("Downloading Cost Center Type report...");
		cctypefunctions.exportExcel();
	}
}
