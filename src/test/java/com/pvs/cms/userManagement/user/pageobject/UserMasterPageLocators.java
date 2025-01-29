package com.pvs.cms.userManagement.user.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMasterPageLocators {

	@FindBy(xpath = "//a[text() = 'Add User']")
	public WebElement addUser;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;
	
	@FindBy(xpath = "//select[@id = 'contact']")
	public WebElement contact;

	@FindBy(xpath = "//input[@id = 'uname']")
	public WebElement username;

	@FindBy(xpath = "//select[@id = 'role']")
	public WebElement userRole;

	@FindBy(xpath = "//select[@id = 'user_status']")
	public WebElement userStatus;

	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//a[text() = 'Cancel']")
	public WebElement btnCancel;

	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;

	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;
	
}
