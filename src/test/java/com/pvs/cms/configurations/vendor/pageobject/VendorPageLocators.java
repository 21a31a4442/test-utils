package com.pvs.cms.configurations.vendor.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VendorPageLocators {

	@FindBy(xpath = "//a[text() = 'Add Vendor']")
	public WebElement addVendor;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'vendor_name']")
	public WebElement name;
	
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
