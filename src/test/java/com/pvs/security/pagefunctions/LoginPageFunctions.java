package com.pvs.security.pagefunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;
import com.pvs.security.pagelocators.LoginPageLocators;
import com.pvs.testframe.base.CommonActions;
import com.pvs.testframe.base.Constants;

public class LoginPageFunctions extends CommonActions {

	LoginPageLocators loginpagelocators;
	WebDriver driver;
	private static Logger logger = LogManager.getLogger(LoginPageFunctions.class);

	public LoginPageFunctions(WebDriver driver) {
		super(driver);
		loginpagelocators = new LoginPageLocators();
		PageFactory.initElements(driver, loginpagelocators);
		this.driver = driver;
	}

	public boolean login(String username, String password) {
		try {
			getTest().get().info("Login with username and password");
			logger.info("login with username and password");
			getUrl(Constants.base_url + Constants.login_url);
			sendKeys(loginpagelocators.username, username, "Username");
			sendKeys(loginpagelocators.password, password, "Password");
			clickElement(loginpagelocators.btnLogin, "Submit");
			if ((driver.getCurrentUrl().equals(Constants.base_url+Constants.login_url))) {
				getTest().get().fail("Login Failed, Wrong username or password");
				logger.error("Login failed, wrong username or password");
				return false;
			} else {
				getTest().get().pass("Login Success");
				logger.info("login Successfull");
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occured during login: " + ex.getMessage());
			getTest().get().fail("Exception occured during login: " + ex.getMessage());
			return false;
		}

	}

	public boolean logout() {
		try {
			logger.info("logging out");
			getTest().get().info("Terminating Current Session");
			clickElement(loginpagelocators.userDropdown, "User Profile Dropdown");
			Thread.sleep(100);
			clickElement(loginpagelocators.logoutOption, "Logout option");
			Thread.sleep(100);
			clickElement(loginpagelocators.btnLogout, "Logout button");
			if(driver.getCurrentUrl().equals(Constants.base_url+Constants.login_url)) {
				getTest().get().pass("Logout Successfull");
				logger.info("Logout successfull");
				return true;
			}else {
				getTest().get().fail("Logout failed");
				logger.info("Logout failed");
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			getTest().get().fail("An exception occured during logout: "+ex.getMessage());
			logger.error("An exception occured during logout: "+ex.getMessage());
			return false;
			
		}
	}
	

}
