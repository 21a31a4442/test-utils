package com.pvs.cms.configurations.miller.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MillerPageLocators {

	@FindBy(xpath = "//a[text() = 'Add Miller Name']")
	public WebElement addMiller;

	@FindBy(xpath = "//input[@type = 'search']")
	public WebElement search;

	@FindBy(xpath = "//input[@id = 'miller_name']")
	public WebElement name;

	@FindBy(xpath = "//input[@id = 'gst_num']")
	public WebElement gstNum;

	@FindBy(xpath = "//input[@id = 'place']")
	public WebElement place;

	@FindBy(xpath = "//input[@id = 'add1']")
	public WebElement address;

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
