package com.pvs.admin.status.testcases;

import static org.testng.Assert.assertEquals;
import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.admin.status.pagefunctions.StatusDataProvider;
import com.pvs.admin.status.pagefunctions.StatusPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.JsonUtil;
import com.pvs.testframe.utils.WebDriverUtil;

public class StatusTest {

	ExtentTestNGITestListener extent;

	WebDriver driver;
	public StatusPageFunctions statuspagefunctions;
	public CommonActions commonactions;

	public StatusTest() {
		driver = WebDriverUtil.getDriver();

		statuspagefunctions = new StatusPageFunctions(driver);
		commonactions = new CommonActions(driver);

	}

	@Ignore
	@Test(priority = 0,dataProvider = "addStatus", dataProviderClass = StatusDataProvider.class)
	public void addStatus(String statusCode, String statusName, String statusModule) {
		ExtentTestNGITestListener.createNode("Add Status");
		getTest().get().info("Adding Status");
		statuspagefunctions.addStatus(statusCode, statusName, statusModule);		
	}
	
	@Test(priority = 1,dataProvider = "editStatus", dataProviderClass = StatusDataProvider.class)
	public void editStatus(String searchStatus, String searchModule, String statusCode, String statusName,
							String statusModule) {
		
		ExtentTestNGITestListener.createNode("Edit Status");
		getTest().get().info("Updating Status");
		statuspagefunctions.editStatus(searchStatus, searchModule, statusCode, statusName, statusModule);
	}
	
	@Ignore
	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Status");
		getTest().get().info("Downloading Status report...");
		statuspagefunctions.exportExcel();
	}

}
