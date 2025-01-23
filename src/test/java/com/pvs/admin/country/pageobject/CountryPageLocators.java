package com.pvs.admin.country.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CountryPageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add Country']")
	public WebElement addCountry;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'country']")
	public WebElement country;
	
	@FindBy(xpath = "//input[@id = 'code']")
	public WebElement code;
	
	@FindBy(xpath = "//input[@id = 'currency']")
	public WebElement currency;

	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//button[@id = 'btnCancel']")
	public WebElement btnCancel;
	
	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;
	
	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;
}
