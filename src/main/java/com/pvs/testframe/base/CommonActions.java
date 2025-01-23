package com.pvs.testframe.base;

import static com.pvs.testframe.listener.ExtentTestNGITestListener.getTest;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions {

	private static Logger logger = LogManager.getLogger(CommonActions.class);
	private WebDriver driver;
	public Select sel;
	public Actions act;

	public CommonActions(WebDriver driver) {
		this.driver = driver;
	}

	public enum ProjectConst {
		EXCEPTIONTEXT("********Exception Occured*********"),
		ELEMENTNOTFOUNDEXCEPTION("****Element not found or Xpath is Null"),
		EXCEPTIONTEXTMETHOD("****Exception occured in the method****"), VALUE("====>");

		private String msg;

		ProjectConst(String s) {
			msg = s;
		}

		public String getMsg() {
			return msg;
		}

	}

	public synchronized void clickElement(WebElement element, String elementName) {
		try {
			logger.info(new StringBuilder("Clicking :").append(elementName));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			getTest().get().pass(elementName + " Clicked successfully");
		} catch (Exception ex) {
			logger.error(ProjectConst.EXCEPTIONTEXT.getMsg() + "\n" + ProjectConst.ELEMENTNOTFOUNDEXCEPTION.getMsg());
			;
			getTest().get().fail(elementName + " Click unsuccessfull: " + ex.getMessage());
		}
	}

	public synchronized void sendKeys(WebElement element, String text, String elementName) {
		try {
			logger.info("Inputing keys: " + text);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(text);
			if (elementName.toLowerCase().contains("password")) {
				getTest().get().pass(elementName + " Typed successfully");
			} else {
				getTest().get().pass(elementName + " '" + text + "' Typed successfully");
			}
		} catch (Exception ex) {
			logger.error(ProjectConst.EXCEPTIONTEXTMETHOD.getMsg() + " " + " sendKeys");
			if (elementName.toLowerCase().contains("password")) {
				getTest().get().fail(elementName + " Type unsuccessfull: " + ex.getMessage());
			}
			getTest().get().fail(elementName + " " + text + " Type unsuccessfull: " + ex.getMessage());
		}
	}

	public synchronized void selectFromDropdown(WebElement element, String value, String elementName) {
		try {
			logger.info("selecting " + value + " from " + elementName + " dropdown");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(element));
			sel = new Select(element);
			sel.selectByVisibleText(value);
			getTest().get().pass("'"+value + "' selected from " + elementName + " dropdown successfully");
		} catch (Exception ex) {
			logger.error(ProjectConst.ELEMENTNOTFOUNDEXCEPTION.getMsg() + " " + " selectFromDropdown");
			getTest().get().fail(
					"Selection of " + value + " from " + elementName + " dropdown unsuccessfull: " + ex.getMessage());
		}
	}

	public synchronized String readTitle() {
		logger.info("Reading title:" + driver.getTitle());
		return driver.getTitle();
	}

	public synchronized void getUrl(String url) {
		try {
			logger.info("Going to url " + url);
			driver.get(url);
			getTest().get().pass("Redirected to " + url);
		} catch (Exception ex) {
			logger.error(ProjectConst.EXCEPTIONTEXT.getMsg() + "\n" + " Error occured while going to url: " + url);
			getTest().get().fail("Error occured while redirecting to " + url);
		}
	}

	public synchronized void closeDriver() {
		try {
			logger.info("Closing driver");
			driver.close();
			getTest().get().pass("Closing driver");
		} catch (Exception ex) {
			logger.error(ProjectConst.EXCEPTIONTEXT.getMsg() + "\n" + " Error while closing driver");
			getTest().get().fail("Error while closing driver" + ex.getMessage());
		}
	}

	public synchronized String getElementName(WebElement element) {
		return element.getText();
	}

	public synchronized void triggerErrormsg() {
		try {
			logger.info("Clicking somewhere else in the page to make the error message visible");
			WebElement body = driver.findElement(By.xpath("//body"));
			body.click();
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error occured while clicking on the body" + ex);
		}
	}

}
