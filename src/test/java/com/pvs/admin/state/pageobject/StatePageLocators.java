package com.pvs.admin.state.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatePageLocators {
	
	@FindBy(xpath = "//a[text() = 'Add State']")
	public WebElement addState;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'state']")
	public WebElement stateName;

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
