package com.MakeMyTrip.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MakeMyTrip.BaseObjects.BaseClass;
import com.aventstack.extentreports.Status;

public class BookingHotel extends BaseClass {

	
	@FindBy(xpath="(//*[@class='blueText pointer latoBold font12'])[1]")
	public WebElement options;
	
	@FindBy(xpath="//*[@id=\"hlistpg_fr_popular_filters\"]//*[text()=\"Great Value Deals\"]")
	public WebElement gd;
	
	@FindBy(xpath="(//*[@id=\"hlistpg_fr_popular_filters\"]//*[contains(text(),\"Free Cancellation\")])[2]")
	public WebElement freecancel;
	
	public void ChooseOptions(){
		try{
		logger.log(Status.INFO, "Show more options ");
			verifyElementIsDisplayed(options);
		options.click();
		logger.log(Status.PASS, "More filters are visible");
	}
		catch(Exception e){
			reportFail(e.getMessage());
		}}
	
	public void GreatDeals(){
		try
		{
			logger.log(Status.INFO, "Use filter of great value deals");
verifyElementIsDisplayed(gd);
		gd.click();
		logger.log(Status.PASS, "Filter applied succesfully");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	
	public void FreeCancellation(){
		try{
			logger.log(Status.INFO, "Use filter of Free Cancellation");
	verifyElementIsDisplayed(freecancel);
		
		waitforspecifictime(2000);
		freecancel.click();
		logger.log(Status.PASS, "Filter applied sucessfully");
		}
		catch(Exception e){
			reportFail(e.getMessage());
		}
	}
	
	public SelectHotel HotelSelection(){
		return PageFactory.initElements(driver, SelectHotel.class);
	}
	
	
}
