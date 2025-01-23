package com.pvs.admin.cctype.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CcTypePageLocators {

	@FindBy(xpath = "//a[text() = 'Add Costcentertype']")
	public WebElement addCctype;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'cc_type']")
	public WebElement ccTypeName;

	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//button[@id = 'btnCancel']")
	public WebElement btnCancel;
	
	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;
	
	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;
	
}
