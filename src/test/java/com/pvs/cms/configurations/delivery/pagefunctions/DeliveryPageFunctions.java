package com.pvs.cms.configurations.delivery.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.delivery.pageobject.DeliveryPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class DeliveryPageFunctions extends CommonActions {

    private WebDriver driver;
    private DeliveryPageLocators deliveryPageLocators;
    private static Logger logger = LogManager.getLogger(DeliveryPageFunctions.class);

    public DeliveryPageFunctions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        deliveryPageLocators = new DeliveryPageLocators();
        PageFactory.initElements(driver, deliveryPageLocators);
    }

    public boolean searchDelivery(String deliveryName) {
        try {
            boolean res = false;
            getUrl(Constants.base_url + Constants.delivery_url);
            logger.info("Searching the delivery name");
            sendKeys(deliveryPageLocators.search, deliveryName, "Searching Delivery: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkDeliveryName = match.findElement(By.xpath(".//td[1]")).getText();
                if (deliveryName.equalsIgnoreCase(checkDeliveryName)) {
                    res = true;
                    break;
                }
            }
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching the delivery: " + ex);
            return false;
        }
    }

    public boolean addDelivery(String deliveryName) {
        try {
            logger.info("Adding Delivery");
            getUrl(Constants.base_url + Constants.delivery_url);
            clickElement(deliveryPageLocators.addDelivery, "Add Delivery");

            sendKeys(deliveryPageLocators.name, deliveryName, "Delivery Name");

            if (deliveryPageLocators.btnSubmit.isEnabled()) {
                clickElement(deliveryPageLocators.btnSubmit, "Submit Button");
                getTest().get().pass("Delivery Name added successfully");
                logger.info("Delivery Name added successfully");
                return true;
            } else {
                clickElement(deliveryPageLocators.btnCancel, "Cancel Button");
                getTest().get().error("Failed to add delivery name");
                logger.info("Failed to add delivery name");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while adding delivery: " + ex);
            return false;
        }
    }

    public boolean editDelivery(String deliveryName) {
        try {
            logger.info("Editing Delivery");
            getUrl(Constants.base_url + Constants.delivery_url);
            WebElement row = searchDeliveryAndReturnRow(deliveryName);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
                clickElement(editBtn, "Edit Button");

                sendKeys(deliveryPageLocators.name, deliveryName, "Delivery Name");

                if (deliveryPageLocators.btnSubmit.isEnabled()) {
                    clickElement(deliveryPageLocators.btnSubmit, "Submit");
                    getTest().get().pass("Delivery Name updated successfully");
                    logger.info("Delivery Name updated successfully");
                    return true;
                } else {
                    clickElement(deliveryPageLocators.btnCancel, "Cancel");
                    getTest().get().error("Failed to update delivery name");
                    logger.info("Failed to update delivery name");
                    return false;
                }
            } else {
                getTest().get().fail("Delivery not found");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while editing delivery: " + ex.getMessage());
            getTest().get().fail("Error while editing delivery: " + ex.getMessage());
            return false;
        }
    }

    public WebElement searchDeliveryAndReturnRow(String deliveryName) {
        try {
            getUrl(Constants.base_url + Constants.delivery_url);
            logger.info("Searching the delivery and return the row");
            sendKeys(deliveryPageLocators.search, deliveryName, "Searching Delivery Table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkDeliveryName = match.findElement(By.xpath(".//td[1]")).getText();
                if (deliveryName.equalsIgnoreCase(checkDeliveryName)) {
                    return match;
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching and returning the row in delivery page: " + ex);
            return null;
        }
    }

    public boolean exportExcel() {
        try {
            logger.info("Clicking export excel button");
            clickElement(deliveryPageLocators.exportExcel, "Export Excel");
            getTest().get().pass("Excel File Downloaded Successfully");
            return true;
        } catch (Exception ex) {
            logger.info("Error while downloading excel file: " + ex.getMessage());
            getTest().get().fail("Error occurred while downloading the excel file: " + ex.getMessage());
            return false;
        }
    }
}
