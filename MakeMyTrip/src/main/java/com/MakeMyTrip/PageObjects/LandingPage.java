package com.MakeMyTrip.PageObjects;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MakeMyTrip.BaseObjects.*;
import com.aventstack.extentreports.Status;


public class LandingPage  extends BaseClass {

	@FindBy(xpath="//*[@id=\"root\"]")
	public WebElement onsite;

	
@FindBy(xpath="//*[@id=\"SW\"]/div[1]/div[1]/ul/li[6]")
public WebElement loginaccount;

@FindBy(xpath="//*[@id=\"username\"]")
public WebElement emailid;

@FindBy(xpath="//*[@id=\"SW\"]/div[1]/div[2]/div[2]/section/form/div[2]/button")
public WebElement continuebtn;

@FindBy(xpath="//*[@id=\"password\"]")
public WebElement password;

@FindBy(xpath="//*[@id=\"SW\"]/div[1]/div[2]/div[2]/section/form/div[2]/button")
public WebElement loginbtn;

@FindBy(xpath="//*[@id=\"otp\"]")
public WebElement otp;

@FindBy(xpath="//*[@id=\"SW\"]/div[1]/div[2]/div[2]/section/form/div[2]/button")
public WebElement otplogin;

public void LoginForm(){
onsite.click();
getTitle("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
	loginaccount.click();
	}

	public void EnterEmailid(String email){
		try{
			logger.log(Status.INFO, "Enter the email id");
		emailid.click();
		emailid.sendKeys(prop.getProperty(email));
		continuebtn.click();
		logger.log(Status.PASS, "Email id entered successfully");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
		}
	
	public void EnterPassword(String pass){
		try{
			logger.log(Status.INFO,"enter password details");
password.click();
password.sendKeys(prop.getProperty(pass));
logger.log(Status.PASS,"password entered successfully");
logger.log(Status.INFO, "Click on login");
loginbtn.click();	
logger.log(Status.PASS,"login button clicked");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}}
	
	public void VerifyingOTP(){
		try{
		logger.log(Status.INFO, "otp verifying");
		otp.click();
		waitforspecifictime(15000);
		otplogin.click();
		logger.log(Status.PASS, "OTP verified");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	
	public SearchHotels BookingHotelPage(){
		
	
		return PageFactory.initElements(driver, SearchHotels.class);
	}
	
	
	
	
	
}
