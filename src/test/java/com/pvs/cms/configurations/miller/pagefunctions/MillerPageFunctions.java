package com.pvs.cms.configurations.miller.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.miller.pageobject.MillerPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class MillerPageFunctions extends CommonActions {

    private WebDriver driver;
    private MillerPageLocators millerPageLocators;
    private static Logger logger = LogManager.getLogger(MillerPageFunctions.class);

    public MillerPageFunctions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        millerPageLocators = new MillerPageLocators();
        PageFactory.initElements(driver, millerPageLocators);
    }

    // Search for Miller by Miller Name
    public boolean searchMiller(String millerName) {
        try {
            boolean res = false;
            int count = 0;
            getUrl(Constants.base_url + Constants.miller_url);
            logger.info("Searching miller by name");

            sendKeys(millerPageLocators.search, millerName, "Searching miller table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkName = match.findElement(By.xpath(".//td[2]")).getText();
                if (millerName.equalsIgnoreCase(checkName)) {
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
            logger.error("Error while searching the miller in the miller page: " + ex);
            return false;
        }
    }

    // Add new miller record
    public boolean addMiller(String millerName, String gstNumber, String place, String address, String status) {
        try {
            logger.info("Adding miller record");
            getUrl(Constants.base_url + Constants.miller_url);
            clickElement(millerPageLocators.addMiller, "Add Miller");

            sendKeys(millerPageLocators.name, millerName, "Miller Name");
            sendKeys(millerPageLocators.gstNum, gstNumber, "GST Number");
            sendKeys(millerPageLocators.place, place, "Place");
            sendKeys(millerPageLocators.address, address, "Address");

            selectFromDropdown(millerPageLocators.status, status, "Status");

            if (millerPageLocators.btnSubmit.isEnabled()) {
                clickElement(millerPageLocators.btnSubmit, "Submit Button");
                getTest().get().pass("Miller added successfully");
                logger.info("Miller added successfully");
                return true;
            } else {
                clickElement(millerPageLocators.btnCancel, "Cancel Button");
                getTest().get().error("Failed to add miller!");
                logger.error("Failed to add miller");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while adding miller: " + ex);
            return false;
        }
    }

    // Edit miller record
    public boolean editMiller(String millerName, String newMillerName, String gstNumber, String place, String address, String status) {
        try {
            logger.info("Editing miller record");
            getUrl(Constants.base_url + Constants.miller_url);
            WebElement row = searchMillerAndReturnRow(millerName);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
                clickElement(editBtn, "Edit Button");

                sendKeys(millerPageLocators.name, newMillerName, "Miller Name");
                sendKeys(millerPageLocators.gstNum, gstNumber, "GST Number");
                sendKeys(millerPageLocators.place, place, "Place");
                sendKeys(millerPageLocators.address, address, "Address");

                selectFromDropdown(millerPageLocators.status, status, "Status");

                if (millerPageLocators.btnSubmit.isEnabled()) {
                    clickElement(millerPageLocators.btnSubmit, "Submit");
                    getTest().get().pass("Miller updated successfully");
                    logger.info("Miller updated successfully");
                    return true;
                } else {
                    clickElement(millerPageLocators.btnCancel, "Cancel");
                    getTest().get().error("Failed to update miller!");
                    logger.error("Failed to update miller");
                    return false;
                }
            } else {
                getTest().get().fail("No such miller found");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("An exception occurred while editing miller: " + ex.getMessage());
            getTest().get().fail("An exception occurred while editing miller: " + ex.getMessage());
            return false;
        }
    }

    // Helper method to search and return the row of miller
    private WebElement searchMillerAndReturnRow(String millerName) {
        try {
            sendKeys(millerPageLocators.search, millerName, "Searching miller table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkName = match.findElement(By.xpath(".//td[2]")).getText();
                if (millerName.equalsIgnoreCase(checkName)) {
                    return match;
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching and returning the miller row: " + ex);
            return null;
        }
    }

    // Export miller details to Excel
    public boolean exportExcel() {
        try {
            logger.info("Clicking export excel button");
            clickElement(millerPageLocators.exportExcel, "Export Excel");
            getTest().get().pass("Excel file downloaded successfully");
            return true;
        } catch (Exception ex) {
            logger.error("Error while downloading excel file: " + ex.getMessage());
            getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
            return false;
        }
    }
}
