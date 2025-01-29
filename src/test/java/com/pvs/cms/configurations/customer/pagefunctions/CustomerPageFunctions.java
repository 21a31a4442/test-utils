package com.pvs.cms.configurations.customer.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.configurations.customer.pageobject.CustomerPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class CustomerPageFunctions extends CommonActions {

    private WebDriver driver;
    private CustomerPageLocators customerPageLocators;
    private static Logger logger = LogManager.getLogger(CustomerPageFunctions.class);

    public CustomerPageFunctions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        customerPageLocators = new CustomerPageLocators();
        PageFactory.initElements(driver, customerPageLocators);
    }

    // Search Customer by Customer Name
    public boolean searchCustomer(String customerName) {
        try {
            getUrl(Constants.base_url + Constants.customer_url);
            logger.info("Searching customer with the name: " + customerName);
            sendKeys(customerPageLocators.search, customerName, "Searching customer: ");
            int count = 0;
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkCustomerName = match.findElement(By.xpath(".//td[1]")).getText();
                if (customerName.equalsIgnoreCase(checkCustomerName)) {
                    count++;
                }
            }
            return count > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching for customer: " + ex);
            return false;
        }
    }

    // Add New Customer
    public boolean addCustomer(String customerName, String address1, String address2, String pincode, String city,
            String state, String country, String primaryContact, String status) {
        try {
            logger.info("Adding customer record");
            getUrl(Constants.base_url + Constants.customer_url);
            clickElement(customerPageLocators.addCustomer, "Add Customer");

            sendKeys(customerPageLocators.name, customerName, "Customer Name");
            sendKeys(customerPageLocators.address1, address1, "Address 1");
            sendKeys(customerPageLocators.address2, address2, "Address 2");
            sendKeys(customerPageLocators.pincode, pincode, "Pincode");

            selectFromDropdown(customerPageLocators.city, city, "City");
            selectFromDropdown(customerPageLocators.state, state, "State");
            selectFromDropdown(customerPageLocators.country, country, "Country");
            selectFromDropdown(customerPageLocators.primContact, primaryContact, "Primary Contact");
            selectFromDropdown(customerPageLocators.status, status, "Status");

            if (customerPageLocators.btnSubmit.isEnabled()) {
                clickElement(customerPageLocators.btnSubmit, "Submit");
                logger.info("Customer added successfully");
                return true;
            } else {
                clickElement(customerPageLocators.btnCancel, "Cancel");
                logger.info("Failed to add customer. Fields may be incomplete.");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while adding customer: " + ex);
            return false;
        }
    }

    // Edit Customer Information
    public boolean editCustomer(String customerName, String address1, String address2, String pincode, String city,
            String state, String country, String primaryContact, String status) {
        try {
            logger.info("Editing customer information");
            getUrl(Constants.base_url + Constants.customer_url);
            WebElement row = searchCustomerAndReturnRow(customerName);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
                clickElement(editBtn, "Edit Customer");

                sendKeys(customerPageLocators.address1, address1, "Address 1");
                sendKeys(customerPageLocators.address2, address2, "Address 2");
                sendKeys(customerPageLocators.pincode, pincode, "Pincode");

                selectFromDropdown(customerPageLocators.city, city, "City");
                selectFromDropdown(customerPageLocators.state, state, "State");
                selectFromDropdown(customerPageLocators.country, country, "Country");
                selectFromDropdown(customerPageLocators.primContact, primaryContact, "Primary Contact");
                selectFromDropdown(customerPageLocators.status, status, "Status");

                if (customerPageLocators.btnSubmit.isEnabled()) {
                    clickElement(customerPageLocators.btnSubmit, "Submit");
                    logger.info("Customer details updated successfully");
                    return true;
                } else {
                    clickElement(customerPageLocators.btnCancel, "Cancel");
                    logger.info("Failed to update customer. Fields may be incomplete.");
                    return false;
                }
            } else {
                logger.info("Customer not found to edit.");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while editing customer: " + ex);
            return false;
        }
    }

    // Helper method to search for a customer and return the row
    private WebElement searchCustomerAndReturnRow(String customerName) {
        try {
            logger.info("Searching for customer to edit");
            sendKeys(customerPageLocators.search, customerName, "Searching customer: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkCustomerName = match.findElement(By.xpath(".//td[1]")).getText();
                if (customerName.equalsIgnoreCase(checkCustomerName)) {
                    return match;
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while searching for customer to edit: " + ex);
            return null;
        }
    }

    // Export customer data to Excel
    public boolean exportExcel() {
        try {
            logger.info("Exporting customer data to Excel");
            clickElement(customerPageLocators.exportExcel, "Export Excel");
            logger.info("Excel file downloaded successfully");
            return true;
        } catch (Exception ex) {
            logger.error("Error while downloading the Excel file: " + ex);
            return false;
        }
    }
}
