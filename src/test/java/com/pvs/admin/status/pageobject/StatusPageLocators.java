package com.pvs.admin.status.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatusPageLocators {

	@FindBy(xpath = "//a[text() = 'Add Status']")
	public WebElement addStatus;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'code']")
	public WebElement statusCode;

	@FindBy(xpath = "//input[@id = 'status']")
	public WebElement statusName;
	
	@FindBy(xpath = "//input[@id = 'module']")
	public WebElement statusModule;
	
	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//a[text() = 'Cancel']")
	public WebElement btnCancel;
	
	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;
	
	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;
	
//	@FindBy(xpath = "//table//tbody//tr//td[3]")
//	public WebElement statusModuleCol;
//	
//	@FindBy(xpath = "//table//tbody//tr//td[2]")
//	public WebElement statusNameCol;
}
