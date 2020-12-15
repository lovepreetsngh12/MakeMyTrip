package com.MakeMyTrip.BusinessComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {
	public static ExtentReports report;
	public static ExtentReports getReportInstance(){
		if(report==null){
			String reportName = DateUtils.getTimeStamp()+".html";
			ExtentHtmlReporter	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\Reports\\"+reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			//We can add lot of information here
			report.setSystemInfo("OS","Windows 10");
			report.setSystemInfo("Environment","UAT");
			htmlReporter.config().setDocumentTitle("UAT REPORT");
			htmlReporter.config().setReportName("Headlines");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			
			
		}
		return report;
	}
}
