package com.pvs.security.testcases;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import com.pvs.security.pagefunctions.LoginPageFunctions;

import com.pvs.testframe.base.Constants;
import com.pvs.testframe.listener.ExtentTestNGITestListener;
import com.pvs.testframe.utils.WebDriverUtil;

public class CommonTests {

	WebDriver driver;
	LoginPageFunctions loginpagefunctions;
//	    private static boolean loginPerformed = false;  // Static flag to check if login is already done

	@BeforeSuite
	public void initSuite() {
		try {
			driver = WebDriverUtil.getDriver();
			if (driver == null) {
				throw new IllegalStateException("WebDriver is null. Initialization failed.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Failed to initialize test suite. Cause: " + ex.getMessage(), ex);
		}
	}

	@BeforeTest
	public void Login() {
		try {
			ExtentTestNGITestListener.createNode("Initializing WebDriver");
			getTest().get().pass("Driver Initialized Successfully");

			ExtentTestNGITestListener.createNode("Setup Login");
			loginpagefunctions = new LoginPageFunctions(driver);
			if (Constants.username == null || Constants.password == null) {
				getTest().get().fail("Login Failed: Username or password is Null. Check config.properties file");
				throw new IllegalArgumentException("Username or password is null. Check Constants configuration.");
			}
			loginpagefunctions.login(Constants.username, Constants.password);

		} catch (Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An Exception Occured while Login: "+ex.getMessage());
			throw new RuntimeException("Login setup failed. Cause: " + ex.getMessage(), ex);
		}
	}

//	@AfterTest
//	public void logout() {
//		try {
//			ExtentTestNGITestListener.createNode("Performing Logout");
//			loginpagefunctions = new LoginPageFunctions(driver);
//			loginpagefunctions.logout();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			getTest().get().fail("An Exception occured while performing Logout: "+ex.getMessage());
//			throw new RuntimeException("Logout failed due to an exception: "+ex.getMessage());
//		}
//	}
	

	
//	@AfterSuite
//	public void destroy() {
//		if (driver != null) {
//			try {
//				ExtentTestNGITestListener.createNode("Quitting Driver...");
//				driver.quit();
//				getTest().get().pass("Driver quit successfully");
//			} catch (Exception ex) {
//				getTest().get().fail("Exception occurred while quitting driver: " + ex.getMessage());
//			}
//		}
//
//		if (ExtentTestNGITestListener.getExtent() != null) {
//			ExtentTestNGITestListener.getExtent().flush();
//		}
//	}
}