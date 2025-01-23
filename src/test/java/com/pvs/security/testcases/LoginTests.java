package com.pvs.security.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.security.pagefunctions.LoginDataProvider;
import com.pvs.security.pagefunctions.LoginPageFunctions;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class LoginTests {
	
	ExtentTestNGITestListener ex;
	WebDriver driver;
	LoginPageFunctions loginpagefunctions;
	CommonActions commonactions;
	
	public LoginTests() {
		driver = WebDriverUtil.getDriver();
		loginpagefunctions = new LoginPageFunctions(driver);
		commonactions = new CommonActions(driver);
	}
	
	@Test(dataProvider = "loginData",dataProviderClass = LoginDataProvider.class)
	public void login(String username,String password) {
		ExtentTestNGITestListener.createNode("Login Test");
		
		boolean login = loginpagefunctions.login(username, password);
		if(!login) {
			getTest().get().skip("Test skipped ");
			throw new SkipException("Test skipped due to invalid username or password");
		}else {
			getTest().get().pass("Login successfull");
		}
	}
	
	@BeforeTest()
	public void defaultLogin() {
		ExtentTestNGITestListener.createNode("Setup Login");
		boolean login = loginpagefunctions.login(Constants.username, Constants.password);
		if(login) {
			getTest().get().pass("Login Successfull");
		}else {
			getTest().get().skip("Test Skipped");
			throw new SkipException("Login failed: Unable to proceed to further tests");
		}
	}
	
	
}
