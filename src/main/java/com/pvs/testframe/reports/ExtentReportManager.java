package com.pvs.testframe.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pvs.testframe.utils.PropertyUtil;

public class ExtentReportManager {

	private static ExtentReports extentreport;
	
	public static ExtentReports createInstance(String filename) {
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(filename);
		
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setReportName("Test Automation Report");
		htmlreport.config().setDocumentTitle(PropertyUtil.getProperties("Automation_Report"));
		htmlreport.config().setChartVisibilityOnOpen(true);
		htmlreport.config().setProtocol(Protocol.HTTPS);
		htmlreport.config().setEncoding("UTF-8");
		htmlreport.config().setTimeStampFormat("MM/dd//yyyy hh:mm:ss a");
		
		extentreport = new ExtentReports();
		extentreport.attachReporter(htmlreport);
		
		return extentreport;
	}
}
