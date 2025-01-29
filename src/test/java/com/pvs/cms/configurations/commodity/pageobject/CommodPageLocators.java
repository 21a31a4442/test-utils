package com.pvs.cms.configurations.commodity.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommodPageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add Commodity']")
	public WebElement addCommodity;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'commodity_name']")
	public WebElement name;

	@FindBy(xpath = "//select[@id = 'cargo_type']")
	public WebElement cargoType;

	@FindBy(xpath = "//input[@id = 'brand']")
	public WebElement brand;

	@FindBy(xpath = "//input[@id = 'marking']")
	public WebElement marking;

	@FindBy(xpath = "//input[@id = 'bag_wt']")
	public WebElement bagWt;
	
	@FindBy(xpath = "//input[@id = 'empty_bag_wt']")
	public WebElement emptyBagWt;

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
