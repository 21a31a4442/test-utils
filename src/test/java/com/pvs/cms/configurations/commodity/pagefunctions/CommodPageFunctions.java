package com.pvs.cms.configurations.commodity.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.commodity.pageobject.CommodPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class CommodPageFunctions extends CommonActions {

    private WebDriver driver;
    private CommodPageLocators commodityPageLocators;
    private static Logger logger = LogManager.getLogger(CommodPageFunctions.class);

    public CommodPageFunctions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        commodityPageLocators = new CommodPageLocators();
        PageFactory.initElements(driver, commodityPageLocators);
    }

    // Search for commodity by Commodity Name and Brand
    public boolean searchCommodity(String commodityName, String brand) {
        try {
            boolean res = false;
            int count = 0;
            getUrl(Constants.base_url + Constants.commodity_url);
            logger.info("Searching commodity by name and brand");

            sendKeys(commodityPageLocators.search, commodityName + " " + brand, "Searching commodity table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkName = match.findElement(By.xpath(".//td[2]")).getText();
                String checkBrand = match.findElement(By.xpath(".//td[3]")).getText();
                if (commodityName.equalsIgnoreCase(checkName) && brand.equalsIgnoreCase(checkBrand)) {
                    res = true;
                    count += 1;
                }
            }

            if (res) {
                return true;
            } else if (count > 1) {
                return false;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching the commodity in the commodity page: " + ex);
            return false;
        }
    }

    // Add new commodity record
    public boolean addCommodity(String commodityName, String brand, String marking, String bagWeight, String emptyBagWeight, String cargoType) {
        try {
            logger.info("Adding commodity record");
            getUrl(Constants.base_url + Constants.commodity_url);
            clickElement(commodityPageLocators.addCommodity, "Add Commodity");

            sendKeys(commodityPageLocators.name, commodityName, "Commodity Name");
            sendKeys(commodityPageLocators.brand, brand, "Brand");
            sendKeys(commodityPageLocators.marking, marking, "Marking");
            sendKeys(commodityPageLocators.bagWt, bagWeight, "Bag Weight");
            sendKeys(commodityPageLocators.emptyBagWt, emptyBagWeight, "Empty Bag Weight");

            selectFromDropdown(commodityPageLocators.cargoType, cargoType, "Cargo Type");

            if (commodityPageLocators.btnSubmit.isEnabled()) {
                clickElement(commodityPageLocators.btnSubmit, "Submit Button");
                getTest().get().pass("Commodity added successfully");
                logger.info("Commodity added successfully");
                return true;
            } else {
                clickElement(commodityPageLocators.btnCancel, "Cancel Button");
                getTest().get().error("Failed to add commodity!");
                logger.error("Failed to add commodity");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while adding commodity: " + ex);
            return false;
        }
    }

    // Edit commodity record
    public boolean editCommodity(String commodityName, String brand, String commodityNewName, String commodityNewBrand, String marking, String bagWeight, String emptyBagWeight, String cargoType, String status) {
        try {
            logger.info("Editing commodity record");
            getUrl(Constants.base_url + Constants.commodity_url);
            WebElement row = searchCommodityAndReturnRow(commodityName, brand);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
                clickElement(editBtn, "Edit Button");

                sendKeys(commodityPageLocators.name, commodityNewName, "Commodity Name");
                sendKeys(commodityPageLocators.brand, commodityNewBrand, "Brand");
                sendKeys(commodityPageLocators.marking, marking, "Marking");
                sendKeys(commodityPageLocators.bagWt, bagWeight, "Bag Weight");
                sendKeys(commodityPageLocators.emptyBagWt, emptyBagWeight, "Empty Bag Weight");

                selectFromDropdown(commodityPageLocators.cargoType, cargoType, "Cargo Type");
                selectFromDropdown(commodityPageLocators.status, status, "Status");

                if (commodityPageLocators.btnSubmit.isEnabled()) {
                    clickElement(commodityPageLocators.btnSubmit, "Submit");
                    getTest().get().pass("Commodity updated successfully");
                    logger.info("Commodity updated successfully");
                    return true;
                } else {
                    clickElement(commodityPageLocators.btnCancel, "Cancel");
                    getTest().get().error("Failed to update commodity!");
                    logger.error("Failed to update commodity");
                    return false;
                }

            } else {
                getTest().get().fail("No such commodity found");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("An exception occurred while editing commodity: " + ex.getMessage());
            getTest().get().fail("An exception occurred while editing commodity: " + ex.getMessage());
            return false;
        }
    }

    // Helper method to search and return the row of commodity
    private WebElement searchCommodityAndReturnRow(String commodityName, String brand) {
        try {
            sendKeys(commodityPageLocators.search, commodityName + " " + brand, "Searching commodity table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkName = match.findElement(By.xpath(".//td[2]")).getText();
                String checkBrand = match.findElement(By.xpath(".//td[3]")).getText();
                if (commodityName.equalsIgnoreCase(checkName) && brand.equalsIgnoreCase(checkBrand)) {
                    return match;
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching and returning the commodity row: " + ex);
            return null;
        }
    }

    // Export commodity details to Excel
    public boolean exportExcel() {
        try {
            logger.info("Clicking export excel button");
            clickElement(commodityPageLocators.exportExcel, "Export Excel");
            getTest().get().pass("Excel file downloaded successfully");
            return true;
        } catch (Exception ex) {
            logger.error("Error while downloading excel file: " + ex.getMessage());
            getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
            return false;
        }
    }
}
