package com.pvs.admin.city.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pvs.admin.city.pagefunctions.CityDataProvider;
import com.pvs.admin.city.pagefunctions.CityPageFunctions;

import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class CityPageTest {

	ExtentTestNGITestListener extent;

	CityPageFunctions cityfunctions;
	CommonActions commonactions;

	WebDriver driver;

	public CityPageTest() {
		driver = WebDriverUtil.getDriver();
		cityfunctions = new CityPageFunctions(driver);
		commonactions = new CommonActions(driver);
	}

	@Test(priority = 0, dataProvider = "addCity", dataProviderClass = CityDataProvider.class)
	public void addCity(String cityName, String state, String country) {
		ExtentTestNGITestListener.createNode("Add City");
		getTest().get().info("Adding new City");
		cityfunctions.addCity(cityName,state,country);
	}

	@Test(priority = 1, dataProvider = "editCity", dataProviderClass = CityDataProvider.class)
	public void editCity(String searchCity, String newCity, String newState, String newCountry) {
		ExtentTestNGITestListener.createNode("Edit City");
		getTest().get().info("Updating City");
		cityfunctions.editCity(searchCity, newCity, newState, newCountry);
	}

	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - City");
		getTest().get().info("Downloading City Report...");
		cityfunctions.exportExcel();
	}
	
}
