package com.pvs.admin.city.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.city.pageobject.CityPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class CityPageFunctions extends CommonActions {

    private WebDriver driver;
    CityPageLocators citypagelocators;
    private static final Logger logger = LogManager.getLogger(CityPageFunctions.class);

    public CityPageFunctions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        citypagelocators = new CityPageLocators();
        PageFactory.initElements(driver, citypagelocators);
    }

    public boolean searchCity(String cityName) {
        try {
            boolean res = false;
            int count = 0;
            logger.info("Searching City by name");
            getUrl(Constants.base_url + Constants.city_url);
            sendKeys(citypagelocators.search, cityName, "Searching City table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkCity = match.findElement(By.xpath(".//td[1]")).getText();
                if (cityName.equalsIgnoreCase(checkCity)) {
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
            logger.error("An error occurred while searching: " + ex.getMessage());
            return false;
        }
    }

    public WebElement searchCityAndReturnRow(String cityName) {
        try {
            logger.info("Searching City by name and returning a row");
            sendKeys(citypagelocators.search, cityName, "Searching City Table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String city = match.findElement(By.xpath("./td[1]")).getText();
                if (city.equalsIgnoreCase(cityName)) {
                    return match;
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("An error occurred while searching and returning row: " + ex.getMessage());
            return null;
        }
    }

    public boolean addCity(String cityName, String stateName, String countryName) {
        try {
            logger.info("Adding City record");
            getUrl(Constants.base_url + Constants.city_url);
            clickElement(citypagelocators.addCity, "Add City");

            sendKeys(citypagelocators.cityName, cityName, "City Name");
            selectFromDropdown(citypagelocators.state, stateName, "State");
            selectFromDropdown(citypagelocators.country, countryName, "Country");

            Thread.sleep(20);
            triggerErrormsg();
            Thread.sleep(100);

            if (citypagelocators.btnSubmit.isEnabled()) {
                clickElement(citypagelocators.btnSubmit, "Submit");
                getTest().get().pass("No duplicates found. City added successfully.");
                logger.info("City added successfully");
                return true;
            } else {
                clickElement(citypagelocators.btnCancel, "Cancel");
                getTest().get().error("Duplicate City found!");
                getTest().get().pass("City has not been added");
                logger.info("Duplicate City found");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            getTest().get().fail("An exception occurred while adding City record: " + ex.getMessage());
            logger.error("Exception occurred while adding City: " + ex.getMessage());
            return false;
        }
    }

    public boolean editCity(String searchCityName, String newCityName, String newStateName, String newCountryName) {
        try {
            logger.info("Editing City");
            getUrl(Constants.base_url + Constants.city_url);
            WebElement row = searchCityAndReturnRow(searchCityName);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
                clickElement(editBtn, "Edit Button");

                sendKeys(citypagelocators.cityName, newCityName, "New City Name");
                selectFromDropdown(citypagelocators.state, newStateName, "New State");
                selectFromDropdown(citypagelocators.country, newCountryName, "New Country");

                Thread.sleep(20);
                triggerErrormsg();
                Thread.sleep(100);

                if (citypagelocators.btnSubmit.isEnabled()) {
                    clickElement(citypagelocators.btnSubmit, "Submit");
                    getTest().get().pass("No duplicates found. City updated successfully.");
                    logger.info("City updated successfully");
                    return true;
                } else {
                    clickElement(citypagelocators.btnCancel, "Cancel");
                    getTest().get().error("Duplicate City found");
                    getTest().get().pass("City has not been updated");
                    logger.info("Duplicate City found");
                    return false;
                }
            }else {
            	getTest().get().fail("No such City present");
            	 return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("An exception occurred while editing City: " + ex.getMessage());
            getTest().get().fail("An exception occurred while editing City: " + ex.getMessage());
            return false;
        }
    }

    public boolean exportExcel() {
        try {
            logger.info("Clicking export excel button");
            clickElement(citypagelocators.exportExcel, "Export Excel");
            getTest().get().pass("Excel File Downloaded successfully");
            return true;
        } catch (Exception ex) {
            logger.info("Error while downloading excel file: " + ex.getMessage());
            getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
            return false;
        }
    }
}
