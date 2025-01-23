package com.pvs.admin.cctype.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.cctype.pageobject.CcTypePageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class CcTypePageFunctions extends CommonActions{
	private WebDriver driver;
	CcTypePageLocators cctypepagelocators;
	private static final Logger logger = LogManager.getLogger(CcTypePageFunctions.class);
	
	
	public CcTypePageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		cctypepagelocators = new CcTypePageLocators();
		PageFactory.initElements(driver, cctypepagelocators);
	}
	
	public boolean searchCctype(String cctype) {
        try {
            boolean res = false;
            int count = 0;
            logger.info("Searching Cost Center Type");
            getUrl(Constants.base_url + Constants.cctype_url);
            sendKeys(cctypepagelocators.search, cctype, "Searching Cost Center Type table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String checkCctype = match.findElement(By.xpath(".//td[1]")).getText();
                if (cctype.equalsIgnoreCase(checkCctype)) {
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

    public WebElement searchCctypeAndReturnRow(String searchCctype) {
        try {
            logger.info("Searching Cost Center Type and returning a row");
            sendKeys(cctypepagelocators.search, searchCctype, "Searching Cost Center Type Table: ");
            for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
                String cctype = match.findElement(By.xpath(".//td[1]")).getText();
                if (cctype.equalsIgnoreCase(searchCctype)) {
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

    public boolean addCctype(String name) {
        try {
            logger.info("Adding Cost Center Type record");
            getUrl(Constants.base_url + Constants.cctype_url);
            clickElement(cctypepagelocators.addCctype, "Add Cost Center Type");

            sendKeys(cctypepagelocators.ccTypeName, name, "Cost Center Type Name");

            Thread.sleep(20);
            triggerErrormsg();
            Thread.sleep(100);

            if (cctypepagelocators.btnSubmit.isEnabled()) {
                clickElement(cctypepagelocators.btnSubmit, "Submit");
                getTest().get().pass("No duplicates found. Cost Center Type added successfully.");
                logger.info("Cost Center Type added successfully");
                return true;
            } else {
                clickElement(cctypepagelocators.btnCancel, "Cancel");
                getTest().get().error("Duplicate Cost Center Type found!");
                getTest().get().pass("Cost Center Type has not been added");
                logger.info("Duplicate Cost Center Type found");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            getTest().get().fail("An exception occurred while adding Cost Center Type record: " + ex.getMessage());
            logger.error("Exception occurred while adding Cost Center Type: " + ex.getMessage());
            return false;
        }
    }

    public boolean editCctype(String searchCctype, String ccTypeName) {
        try {
            logger.info("Editing Cost Center Type");
            getUrl(Constants.base_url + Constants.cctype_url);
            WebElement row = searchCctypeAndReturnRow(searchCctype);
            if (row != null) {
                WebElement editBtn = row.findElement(By.xpath("//td[2]//a"));
                clickElement(editBtn, "Edit Button");
                sendKeys(cctypepagelocators.ccTypeName, ccTypeName, "Cost Center Type Name");

                Thread.sleep(20);
                triggerErrormsg();
                Thread.sleep(100);

                if (cctypepagelocators.btnSubmit.isEnabled()) {
                    clickElement(cctypepagelocators.btnSubmit, "Submit");
                    getTest().get().pass("No duplicates found. Cost Center Type updated successfully.");
                    logger.info("Cost Center Type updated successfully");
                    return true;
                } else {
                    clickElement(cctypepagelocators.btnCancel, "Cancel");
                    getTest().get().error("Duplicate Cost Center Type found");
                    getTest().get().pass("Cost Center Type has not been updated");
                    logger.info("Duplicate Cost Center Type found");
                    return false;
                }
            }else {
            	getTest().get().fail("No such Cost Center Type present");
            	 return false;
            }
           
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("An exception occurred while editing Cost Center Type: " + ex.getMessage());
            getTest().get().fail("An exception occurred while editing Cost Center Type: " + ex.getMessage());
            return false;
        }
    }

    public boolean exportExcel() {
        try {
            logger.info("Clicking export excel button");
            clickElement(cctypepagelocators.exportExcel, "Export Excel");
            getTest().get().pass("Excel File Downloaded successfully");
            return true;
        } catch (Exception ex) {
            logger.info("Error while downloading excel file: " + ex.getMessage());
            getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
            return false;
        }
    }

	
	
}
