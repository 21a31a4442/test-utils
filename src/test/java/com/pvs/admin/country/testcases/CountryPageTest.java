package com.pvs.admin.country.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import com.pvs.admin.country.pagefunctions.CountryDataProvider;
import com.pvs.admin.country.pagefunctions.CountryPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class CountryPageTest {
	
	ExtentTestNGITestListener extent;
	
	CountryPageFunctions countryfunctions;
	CommonActions commonactions;
	
	WebDriver driver;
	
	public CountryPageTest() {
		driver = WebDriverUtil.getDriver();
		countryfunctions = new CountryPageFunctions(driver);
		commonactions = new CommonActions(driver);
	}
	
	@Test(priority = 0,dataProvider = "addCountry", dataProviderClass = CountryDataProvider.class)
	public void addCountry(String country,String code, String currency) {
		ExtentTestNGITestListener.createNode("Add Country");
		getTest().get().info("Adding new Country");
		countryfunctions.addCountry(country, code, currency);
	}
	
	@Test(priority = 1, dataProvider = "editCountry", dataProviderClass = CountryDataProvider.class)
	public void editCountry(String searchCountry, String searchCode, String searchCurrency,
							String newCountry, String newCode, String newCurrency) {
		ExtentTestNGITestListener.createNode("Edit Country");
		getTest().get().info("Updating Country");
		countryfunctions.editCountry(searchCountry, searchCode, searchCurrency, newCountry, newCode, newCurrency);
	}
	
	@Test(priority = 2)
	public void exportExcel() {
		ExtentTestNGITestListener.createNode("Export Excel - Country");
		getTest().get().info("Downloading Country Report...");
		countryfunctions.exportExcel();
	}
								
	
	
	

}
