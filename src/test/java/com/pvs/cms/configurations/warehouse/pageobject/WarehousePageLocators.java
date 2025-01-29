package com.pvs.cms.configurations.warehouse.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WarehousePageLocators {

	@FindBy(xpath = "//a[text() = 'Add Warehouse']")
	public WebElement addWarehouse;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'warehouse_name']")
	public WebElement whouseName;
	
	@FindBy(xpath = "//input[@id = 'code']")
	public WebElement whouseCode;
	
	@FindBy(xpath = "//select[@id = 'lessor']")
	public WebElement lessor;
	
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
	
	@FindBy(xpath = "//input[@id = 'capacity_sqft']")
	public WebElement capacitySqft;

	@FindBy(xpath = "//input[@id = 'capacity_mton']")
	public WebElement capacityMton;
	
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


