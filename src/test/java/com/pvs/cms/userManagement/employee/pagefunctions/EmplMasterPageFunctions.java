package com.pvs.cms.userManagement.employee.pagefunctions;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.pvs.cms.userManagement.employee.pageobject.EmplMasterPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class EmplMasterPageFunctions extends CommonActions {

    private WebDriver driver;
    private EmplMasterPageLocators emplMasterPageLocators;
    private static Logger logger = LogManager.getLogger(EmplMasterPageFunctions.class);

    public EmplMasterPageFunctions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        emplMasterPageLocators = new EmplMasterPageLocators();
        PageFactory.initElements(driver, emplMasterPageLocators);
    }

    // Search Employee by Email
    public boolean searchEmployee(String email) {
        try {
            logger.info("Searching for employee using email");
            sendKeys(emplMasterPageLocators.search, email, "Searching Employee by Email: ");
            // Check if the employee exists in the table by verifying the email
            for (WebElement row : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkEmail = row.findElement(By.xpath(".//td[4]")).getText();
                if (email.equalsIgnoreCase(checkEmail)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while searching the employee: " + ex);
            return false;
        }
    }

    // Add Employee
    public boolean addEmployee(String firstName, String lastName, String dob, String email, String mobile, 
                                String address1, String address2, String pincode, String city, String state, 
                                String country, String employeeType, String doj, String exitDate, String entity, 
                                String department, String designation, String imagePath, String personalEmail) {
        try {
            logger.info("Adding new Employee/Consultant");
            getUrl(Constants.base_url + Constants.employee_url);
            clickElement(emplMasterPageLocators.addEmployee, "Add Employee");

            sendKeys(emplMasterPageLocators.firstName, firstName, "First Name");
            sendKeys(emplMasterPageLocators.lastName, lastName, "Last Name");
            sendKeys(emplMasterPageLocators.dob, dob, "Date of Birth");
            sendKeys(emplMasterPageLocators.email, email, "Email");
            sendKeys(emplMasterPageLocators.personalEmail, personalEmail, "Personal Email");
            sendKeys(emplMasterPageLocators.mobile, mobile, "Mobile");
            sendKeys(emplMasterPageLocators.address1, address1, "Address 1");
            sendKeys(emplMasterPageLocators.address2, address2, "Address 2");
            sendKeys(emplMasterPageLocators.pincode, pincode, "Pincode");

            selectFromDropdown(emplMasterPageLocators.city, city, "City");
            selectFromDropdown(emplMasterPageLocators.state, state, "State");
            selectFromDropdown(emplMasterPageLocators.country, country, "Country");
            selectFromDropdown(emplMasterPageLocators.employeeType, employeeType, "Employee Type");
            sendKeys(emplMasterPageLocators.doj, doj, "Date of Joining");
            sendKeys(emplMasterPageLocators.exitDate, exitDate, "Exit Date");

            selectFromDropdown(emplMasterPageLocators.entity, entity, "Entity");
            selectFromDropdown(emplMasterPageLocators.department, department, "Department");
            selectFromDropdown(emplMasterPageLocators.designation, designation, "Designation");

            // Upload image
//            uploadFile(emplMasterPageLocators.image, imagePath, "Image");

            Thread.sleep(20);
            triggerErrormsg();
            Thread.sleep(100);

            if (emplMasterPageLocators.btnSubmit.isEnabled()) {
                clickElement(emplMasterPageLocators.btnSubmit, "Submit Button");
                getTest().get().pass("Employee added successfully");
                logger.info("Employee added successfully");
                return true;
            } else {
                clickElement(emplMasterPageLocators.btnCancel, "Cancel Button");
                getTest().get().error("Duplicate or invalid data found");
                logger.info("Error: Duplicate or invalid data found while adding employee");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while adding employee: " + ex);
            return false;
        }
    }

    // Edit Employee
    public boolean editEmployee(String email, String firstName, String lastName, String dob, String mobile, 
                                 String address1, String address2, String pincode, String city, String state, 
                                 String country, String employeeType, String doj, String exitDate, String entity, 
                                 String department,String status,String designation, String imagePath,String personalEmail) {
        try {
            logger.info("Editing employee details");
            getUrl(Constants.base_url + Constants.employee_url);

            // Search for the employee by email
            WebElement row = searchEmployeeAndReturnRow(email);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[9]//a"));
                clickElement(editBtn, "Edit Button");

                sendKeys(emplMasterPageLocators.firstName, firstName, "First Name");
                sendKeys(emplMasterPageLocators.lastName, lastName, "Last Name");
                sendKeys(emplMasterPageLocators.dob, dob, "Date of Birth");
                sendKeys(emplMasterPageLocators.email, email, "Email");
                sendKeys(emplMasterPageLocators.personalEmail, personalEmail, "Personal Email");
                sendKeys(emplMasterPageLocators.mobile, mobile, "Mobile");
                sendKeys(emplMasterPageLocators.address1, address1, "Address 1");
                sendKeys(emplMasterPageLocators.address2, address2, "Address 2");
                sendKeys(emplMasterPageLocators.pincode, pincode, "Pincode");

                selectFromDropdown(emplMasterPageLocators.empStatus, status, "Employee Status");
                selectFromDropdown(emplMasterPageLocators.city, city, "City");
                selectFromDropdown(emplMasterPageLocators.state, state, "State");
                selectFromDropdown(emplMasterPageLocators.country, country, "Country");
                selectFromDropdown(emplMasterPageLocators.employeeType, employeeType, "Employee Type");
                sendKeys(emplMasterPageLocators.doj, doj, "Date of Joining");
                sendKeys(emplMasterPageLocators.exitDate, exitDate, "Exit Date");

                selectFromDropdown(emplMasterPageLocators.entity, entity, "Entity");
                selectFromDropdown(emplMasterPageLocators.department, department, "Department");
                selectFromDropdown(emplMasterPageLocators.designation, designation, "Designation");

                // Upload image
//                uploadFile(emplMasterPageLocators.image, imagePath, "Image");

                Thread.sleep(20);
                triggerErrormsg();
                Thread.sleep(100);

                if (emplMasterPageLocators.btnSubmit.isEnabled()) {
                    clickElement(emplMasterPageLocators.btnSubmit, "Submit Button");
                    getTest().get().pass("Employee updated successfully");
                    logger.info("Employee updated successfully");
                    return true;
                } else {
                    clickElement(emplMasterPageLocators.btnCancel, "Cancel Button");
                    getTest().get().error("Duplicate or invalid data found");
                    logger.info("Error: Duplicate or invalid data found while editing employee");
                    return false;
                }
            } else {
                getTest().get().fail("Employee with email " + email + " not found");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error occurred while editing employee: " + ex);
            return false;
        }
    }

    // Export Excel
    public boolean exportExcel() {
        try {
            logger.info("Clicking export excel button");
            clickElement(emplMasterPageLocators.exportExcel, "Export Excel");
            getTest().get().pass("Excel File Downloaded Successfully");
            return true;
        } catch (Exception ex) {
            logger.info("Error while downloading excel file: " + ex.getMessage());
            getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
            return false;
        }
    }

    // Helper method to search and return employee row by email
    private WebElement searchEmployeeAndReturnRow(String email) {
        for (WebElement row : driver.findElements(By.xpath("//table//tbody//tr"))) {
            String checkEmail = row.findElement(By.xpath(".//td[4]")).getText();
            if (email.equalsIgnoreCase(checkEmail)) {
                return row;
            }
        }
        return null;
    }
}
