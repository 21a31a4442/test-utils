package com.pvs.admin.city.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CityPageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add City']")
	public WebElement addCity;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'city']")
	public WebElement cityName;	
	
	@FindBy(xpath = "//select[@id = 'state']")
	public WebElement state;

	@FindBy(xpath = "//select[@id = 'country']")
	public WebElement country;

	@FindBy(xpath = "//button[@id = 'btnSubmit']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//a[text() = 'Cancel']")
	public WebElement btnCancel;

	@FindBy(xpath = "//a[text()= ' Export Excel']")
	public WebElement exportExcel;

	@FindBy(xpath = "//table//tbody//tr//td[4]")
	public WebElement btnEdit;

}
