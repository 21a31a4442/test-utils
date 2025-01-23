package com.pvs.admin.costcenter.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CostCenterPageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add Costcenter']")
	public WebElement addCostcenter;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'cc_code']")
	public WebElement ccCode;

	@FindBy(xpath = "//input[@id = 'entity_id']")
	public WebElement entityParent;

	@FindBy(xpath = "//input[@id = 'incorp_date']")
	public WebElement incorpDate;

	@FindBy(xpath = "//input[@id = 'gst_no']")
	public WebElement gstNum;

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

	@FindBy(xpath = "//select[@id = 'primary_contact']")
	public WebElement primContact;

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
