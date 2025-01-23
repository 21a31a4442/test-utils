package com.pvs.admin.designation.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DesnPageLocators {

	@FindBy(xpath = "//a[text() = 'Add Designation']")
	public WebElement addDesn;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'name']")
	public WebElement desnName;

	@FindBy(xpath = "//input[@id = 'code']")
	public WebElement desnCode;

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
