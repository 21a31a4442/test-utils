package com.pvs.security.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {

	
	@FindBy( name = "uname")
	public WebElement username;
	
	@FindBy(name = "password")
	public WebElement password;
	
	@FindBy(name = "login")
	public WebElement btnLogin;
	
	@FindBy(name = "email")
	public WebElement email;
	
	@FindBy(name = "check-email")
	public WebElement checkEmail;
	
	@FindBy(xpath = "//div[@class = 'alert alert-danger text-center']")
	public WebElement alertInvalidEmail;
	
	@FindBy(xpath = "//a[text()  = 'Forgot password?']")
	public WebElement forgotPwd;
	
	@FindBy(xpath = "//a[@id = 'userDropdown']")
	public WebElement userDropdown;
	
	@FindBy(xpath = "//a[@data-target = '#logoutModal']")
	public WebElement logoutOption;
	
	@FindBy(xpath = "//button[@name = 'logout_btn']")
	public WebElement btnLogout;
	
}
