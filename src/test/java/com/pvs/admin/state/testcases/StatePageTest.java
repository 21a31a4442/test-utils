package com.pvs.admin.state.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pvs.admin.state.pagefunctions.StateDataProvider;
import com.pvs.admin.state.pagefunctions.StatePageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class StatePageTest {

	ExtentTestNGITestListener extent;

	StatePageFunctions statefunctions;
	CommonActions commonactions;

	WebDriver driver;

	public StatePageTest() {
		driver = WebDriverUtil.getDriver();
		statefunctions = new StatePageFunctions(driver);
		commonactions = new CommonActions(driver);
	}

	@Test(priority = 0, dataProvider = "addState", dataProviderClass = StateDataProvider.class)
	public void addState(String stateName, String country) {
		ExtentTestNGITestListener.createNode("Add State");
		getTest().get().info("Adding new State");
		statefunctions.addState(stateName,country);
	}

	@Test(priority = 1, dataProvider = "editState", dataProviderClass = StateDataProvider.class)
	public void editState(String searchState, String newState, String newCountry) {
		ExtentTestNGITestListener.createNode("Edit State");
		getTest().get().info("Updating State");
		statefunctions.editState(searchState, newState, newCountry);
	}

	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - State");
		getTest().get().info("Downloading State Report...");
		statefunctions.exportExcel();
	}
}
