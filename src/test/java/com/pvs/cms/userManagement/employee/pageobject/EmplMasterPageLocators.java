package com.pvs.cms.userManagement.employee.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmplMasterPageLocators {

	@FindBy(xpath = "//a[text() = 'Add Employee/Consultant']")
	public WebElement addEmployee;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'f_name']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@id = 'l_name']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@id = 'dob']")
	public WebElement dob;

	@FindBy(xpath = "//input[@id = 'email']")
	public WebElement email;

	@FindBy(xpath = "//input[@id = 'personal_email']")
	public WebElement personalEmail;

	@FindBy(xpath = "//input[@id = 'mobile']")
	public WebElement mobile;

	@FindBy(xpath = "//input[@id = 'add1']")
	public WebElement address1;

	@FindBy(xpath = "//input[@id = 'add2']")
	public WebElement address2;

	@FindBy(xpath = "//input[@id = 'pin']")
	public WebElement pincode;

	@FindBy(xpath = "//select[@id = 'city']")
	public WebElement city;

	@FindBy(xpath = "//select[@id = 'state']")
	public WebElement state;

	@FindBy(xpath = "//select[@id = 'country']")
	public WebElement country;

	@FindBy(xpath = "//select[@id = 'etype']")
	public WebElement employeeType;

	@FindBy(xpath = "//input[@id = 'doj']")
	public WebElement doj;

	@FindBy(xpath = "//input[@id = 'exit_date']")
	public WebElement exitDate;

	@FindBy(xpath = "//select[@id = 'entity']")
	public WebElement entity;

	@FindBy(xpath = "//select[@id = 'department']")
	public WebElement department;

	@FindBy(xpath = "//select[@id = 'designation']")
	public WebElement designation;

	@FindBy(xpath = "//input[@id = 'image']")
	public WebElement image;

	@FindBy(xpath = "//select[@id = 'emp_status")
	public WebElement empStatus;

	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//a[text() = 'Cancel']")
	public WebElement btnCancel;

	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;

	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;

}
