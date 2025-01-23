package com.pvs.admin.ctype.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactTypePageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add Contacttype']")
	public WebElement addCtype;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'name']")
	public WebElement ctypeName;

	@FindBy(xpath = "//select[@id = 'status']")
	public WebElement status;
	
	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//button[@id = 'btnCancel']")
	public WebElement btnCancel;
	
	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;
	
	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;
}
