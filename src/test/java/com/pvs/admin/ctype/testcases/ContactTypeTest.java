package com.pvs.admin.ctype.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import com.pvs.admin.ctype.pagefunctions.ContactTypeDataProvider;
import com.pvs.admin.ctype.pagefunctions.ContactTypePageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class ContactTypeTest {
	
	ExtentTestNGITestListener extent;
	
	WebDriver driver;
	public ContactTypePageFunctions ctypefunctions;
	public CommonActions commonactions;
	
	public ContactTypeTest() {
		driver = WebDriverUtil.getDriver();
		
		ctypefunctions = new ContactTypePageFunctions(driver);
		commonactions = new CommonActions(driver);
	}
	
//	@Ignore
	@Test(priority = 0,dataProvider = "addCType",dataProviderClass = ContactTypeDataProvider.class)
	public void addCtype(String ctypeName, String status) {
		
		ExtentTestNGITestListener.createNode("Add Contact Type");
		getTest().get().info("Adding Contact Type");
//		System.out.println(status);
		ctypefunctions.addCtype(ctypeName, status);
	}
	
	@Ignore
	@Test(priority = 1,dataProvider = "editCType",dataProviderClass = ContactTypeDataProvider.class)
	public void editCtype(String searchCtype, String searchStatus, String ctypeName, String status) {
		
		ExtentTestNGITestListener.createNode("Edit Contact Type");
		getTest().get().info("Updating Contact Type");
		ctypefunctions.editCtype(searchCtype, searchStatus, ctypeName, status);
	}
	
//	@Ignore
	@Test(priority = 2)
	public void exportExcel() {
		
		ExtentTestNGITestListener.createNode("Export Excel - Contact Type");
		getTest().get().info("Downloading Contact Type report...");
		ctypefunctions.exportExcel();
	}

}
