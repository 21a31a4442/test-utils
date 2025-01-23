package com.pvs.admin.country.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.country.pageobject.CountryPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class CountryPageFunctions extends CommonActions {

	private WebDriver driver;
	CountryPageLocators countrypagelocators;
	private static final Logger logger = LogManager.getLogger(CountryPageFunctions.class);

	public CountryPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		countrypagelocators = new CountryPageLocators();
		PageFactory.initElements(driver, countrypagelocators);
	}

	public boolean searchCountry(String countryName, String countryCode, String currency) {
		try {
			boolean res = false;
			int count = 0;
			logger.info("Searching Country by name, code, or currency");
			getUrl(Constants.base_url + Constants.country_url);

			sendKeys(countrypagelocators.search, countryName + " " + countryCode + " " + currency,
					"Searching Country table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkCountry = match.findElement(By.xpath(".//td[1]")).getText();
				String checkCode = match.findElement(By.xpath(".//td[2]")).getText();
				String checkCurrency = match.findElement(By.xpath(".//td[3]")).getText();

				if (countryName.equalsIgnoreCase(checkCountry) && countryCode.equalsIgnoreCase(checkCode)
						&& currency.equalsIgnoreCase(checkCurrency)) {
					res = true;
					count += 1;
				}
			}
			if (res) {
				return true;
			} else if (count > 1) {
				return false;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An Error occurred while searching: " + ex.getMessage());
			return false;
		}
	}

	public WebElement searchCountryAndReturnRow(String countryName, String countryCode, String currency) {
		try {
			logger.info("Searching Country by name, code, or currency and returning a row");
			sendKeys(countrypagelocators.search, countryName + " " + countryCode + " " + currency,
					"Searching Country Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String country = match.findElement(By.xpath("./td[1]")).getText();
				String code = match.findElement(By.xpath("./td[2]")).getText();
				String curr = match.findElement(By.xpath("./td[3]")).getText();

				if (country.equalsIgnoreCase(countryName) && code.equalsIgnoreCase(countryCode)
						&& curr.equalsIgnoreCase(currency)) {
					return match;
				}
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An Error occurred while searching and returning row: " + ex.getMessage());
			return null;
		}
	}

	public boolean addCountry(String countryName, String countryCode, String currency) {
		try {
			logger.info("Adding Country record");
			getUrl(Constants.base_url + Constants.country_url);
			clickElement(countrypagelocators.addCountry, "Add Country");

			sendKeys(countrypagelocators.country, countryName, "Country Name");
			sendKeys(countrypagelocators.code, countryCode, "Country Code");
			sendKeys(countrypagelocators.currency, currency, "Currency");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (countrypagelocators.btnSubmit.isEnabled()) {
				clickElement(countrypagelocators.btnSubmit, "Submit");
				getTest().get().pass("No duplicates found. Country added successfully.");
				logger.info("Country added successfully");
				return true;
			} else {
				clickElement(countrypagelocators.btnCancel, "Cancel");
				getTest().get().error("Duplicate Country found!");
				getTest().get().pass("Country has not been added");
				logger.info("Duplicate Country found");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while adding Country record: " + ex.getMessage());
			logger.error("Exception occurred while adding Country: " + ex.getMessage());
			return false;
		}
	}

	public boolean editCountry(String searchCountryName, String searchCountryCode, String searchCurrency,
			String newCountryName, String newCountryCode, String newCurrency) {
		try {
			logger.info("Editing Country");
			getUrl(Constants.base_url + Constants.country_url);
			WebElement row = searchCountryAndReturnRow(searchCountryName, searchCountryCode, searchCurrency);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(countrypagelocators.country, newCountryName, "New Country Name");
				sendKeys(countrypagelocators.code, newCountryCode, "New Country Code");
				sendKeys(countrypagelocators.currency, newCurrency, "New Currency");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (countrypagelocators.btnSubmit.isEnabled()) {
					clickElement(countrypagelocators.btnSubmit, "Submit");
					getTest().get().pass("No duplicates found. Country updated successfully.");
					logger.info("Country updated successfully");
					return true;
				} else {
					clickElement(countrypagelocators.btnCancel, "Cancel");
					getTest().get().error("Duplicate Country found");
					getTest().get().pass("Country has not been updated");
					logger.info("Duplicate Country found");
					return false;
				}
			} else {
				getTest().get().fail("No such Country present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("An exception occurred while editing Country: " + ex.getMessage());
			getTest().get().fail("An exception occurred while editing Country: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			logger.info("Clicking export excel button");
			clickElement(countrypagelocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully");
			return true;
		} catch (Exception ex) {
			logger.info("Error while downloading excel file: " + ex.getMessage());
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}
}
