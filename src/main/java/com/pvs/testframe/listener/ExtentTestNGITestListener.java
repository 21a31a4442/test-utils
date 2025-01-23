package com.pvs.testframe.listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pvs.testframe.reports.ExtentReportManager;

public class ExtentTestNGITestListener extends TestListenerAdapter {

    private static final String dateName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
    public static final String USER_DIR = System.getProperty("user.dir");
    private static final String reportDirectoryName = "/reports/extentreport_" + dateName;
    private static ExtentReports extentreport;
    private static final Logger logger = LogManager.getLogger(ExtentTestNGITestListener.class);
    public static String htmlReportName;

    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

//    public static ExtentReports getExtent() {
//        if (extentreport == null) {
//            extentreport = ExtentReportManager.createInstance(USER_DIR + "/" + htmlReportName);
//        }
//        return extentreport;  // Return the ExtentReports instance
//    }

    
    public static ExtentReports getExtent() {
    	if(extentreport == null) {
    		File reportDir = new File(USER_DIR+reportDirectoryName);
    		if(!reportDir.exists()) {
    			boolean isCreated = reportDir.mkdirs();
    			if(!isCreated) {
    				logger.error("Error while creating test report directory, Check the path");
    			}
    		}
    		htmlReportName = reportDirectoryName +"/"+"TestReport"+"_"+dateName+".html";
    		logger.info("Report location: {}/{}",USER_DIR,htmlReportName);
    		
    		extentreport = ExtentReportManager.createInstance(USER_DIR+"/"+htmlReportName);

    	}
    	return extentreport;
    }
    


    @Override
    public void onStart(ITestContext testContext) {
        try {
            if (extentreport == null) {
                getExtent();
            }
            ExtentTest parent = extentreport.createTest(testContext.getName()); //testContext.getName() returns the name of the test which is declare in testng.xml file 
            parentTest.set(parent);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to initialize ExtentTest for suite. Cause: " + ex.getMessage(), ex);
        }
    }


    @Override
    public void onFinish(ITestContext testContext) {
        logger.info("Finishing test suite: {}", testContext.getName());
        extentreport.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (parentTest.get() == null) {
        }
    }

    public static synchronized void createNode(String type) {
        if (parentTest.get() == null) {
            throw new IllegalStateException("Parent test is not initialized. Ensure onStart() is executed.");
        }
        ExtentTest child = parentTest.get().createNode(type);
        test.set(child);
    }


    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info("Test Passed: {}", tr.getMethod().getMethodName());
        getTest().get().pass("Test passed: " + tr.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.error("Test Failed: {}", tr.getMethod().getMethodName());
        getTest().get().fail("Test Failed: " + tr.getMethod().getMethodName());
        if (tr.getThrowable() != null) {
            getTest().get().fail("Test Failed due to: " + tr.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.warn("Test Skipped: {}", tr.getMethod().getMethodName());
        getTest().get().warning("Test Skipped: " + tr.getMethod().getMethodName());
    }

    public static ThreadLocal<ExtentTest> getTest() {
        return test;
    }

    public static ThreadLocal<ExtentTest> getParentTest() {
        return parentTest;
    }
}

