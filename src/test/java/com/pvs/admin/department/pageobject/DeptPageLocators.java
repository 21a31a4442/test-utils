package com.pvs.admin.department.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeptPageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add Department']")
	public WebElement addDept;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'name']")
	public WebElement deptName;
	
	@FindBy(xpath = "//input[@id = 'code']")
	public WebElement deptCode;

	@FindBy(xpath = "//select[@id = 'status']")
	public WebElement status;
	
	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//a[text() = 'Cancel']")
	public WebElement btnCancel;
	
	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;
	
	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;
}
