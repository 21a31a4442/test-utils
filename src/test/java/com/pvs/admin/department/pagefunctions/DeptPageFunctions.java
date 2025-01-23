package com.pvs.admin.department.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.admin.department.pageobject.DeptPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class DeptPageFunctions extends CommonActions {

	private WebDriver driver;
	DeptPageLocators deptpagelocators;
	private static final Logger logger = LogManager.getLogger(DeptPageFunctions.class);

	public DeptPageFunctions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		deptpagelocators = new DeptPageLocators();
		PageFactory.initElements(driver, deptpagelocators);
	}

	public boolean searchDept(String deptName, String deptCode) {
		try {
			boolean res = false;
			int count = 0;
			getUrl(Constants.base_url + Constants.dept_url);
			sendKeys(deptpagelocators.search, deptName + " " + deptCode, "Searching Department table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String checkDeptName = match.findElement(By.xpath(".//td[1]")).getText();
				String checkDeptCode = match.findElement(By.xpath(".//td[2]")).getText();
				if (deptName.equalsIgnoreCase(checkDeptName) && deptCode.equalsIgnoreCase(checkDeptCode)) {
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

	public WebElement searchDeptAndReturnRow(String deptName, String deptCode) {
		try {
			sendKeys(deptpagelocators.search, deptName + " " + deptCode, "Searching Department Table: ");
			for (WebElement match : driver.findElements(By.xpath("//table//tbody//tr"))) {
				String name = match.findElement(By.xpath("./td[1]")).getText();
				String code = match.findElement(By.xpath(".//td[2]")).getText();
				if (name.equalsIgnoreCase(deptName) && code.equalsIgnoreCase(deptCode)) {
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

	public boolean addDept(String deptName, String deptCode, String status) {
		try {
			getUrl(Constants.base_url + Constants.dept_url);
			clickElement(deptpagelocators.addDept, "Add Department");

			sendKeys(deptpagelocators.deptName, deptName, "Department Name");
			sendKeys(deptpagelocators.deptCode, deptCode, "Department Code");
			selectFromDropdown(deptpagelocators.status, status, "Status");

			Thread.sleep(20);
			triggerErrormsg();
			Thread.sleep(100);

			if (deptpagelocators.btnSubmit.isEnabled()) {
				clickElement(deptpagelocators.btnSubmit, "Submit");
				getTest().get().pass("No duplicates found. Department added successfully.");
				return true;
			} else {
				clickElement(deptpagelocators.btnCancel, "Cancel");
				getTest().get().error("Duplicate Department found!");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while adding Department: " + ex.getMessage());
			return false;
		}
	}

	public boolean editDept(String searchDeptName, String searchDeptCode, String newDeptName, String newDeptCode,
			String newStatus) {
		try {
			getUrl(Constants.base_url + Constants.dept_url);
			WebElement row = searchDeptAndReturnRow(searchDeptName, searchDeptCode);
			if (row != null) {
				WebElement editBtn = row.findElement(By.xpath("//td[4]//a"));
				clickElement(editBtn, "Edit Button");

				sendKeys(deptpagelocators.deptName, newDeptName, "New Department Name");
				sendKeys(deptpagelocators.deptCode, newDeptCode, "New Department Code");
				selectFromDropdown(deptpagelocators.status, newStatus, "New Status");

				Thread.sleep(20);
				triggerErrormsg();
				Thread.sleep(100);

				if (deptpagelocators.btnSubmit.isEnabled()) {
					clickElement(deptpagelocators.btnSubmit, "Submit");
					getTest().get().pass("No duplicates found. Department updated successfully.");
					return true;
				} else {
					clickElement(deptpagelocators.btnCancel, "Cancel");
					getTest().get().error("Duplicate Department found");
					return false;
				}
			} else {
				getTest().get().fail("No such Department present");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occurred while editing Department: " + ex.getMessage());
			return false;
		}
	}

	public boolean exportExcel() {
		try {
			clickElement(deptpagelocators.exportExcel, "Export Excel");
			getTest().get().pass("Excel File Downloaded successfully");
			return true;
		} catch (Exception ex) {
			getTest().get().fail("An exception occurred while downloading the excel file: " + ex.getMessage());
			return false;
		}
	}
}
