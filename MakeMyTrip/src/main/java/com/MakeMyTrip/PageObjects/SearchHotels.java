package com.MakeMyTrip.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MakeMyTrip.BaseObjects.BaseClass;
import com.aventstack.extentreports.Status;

public class SearchHotels extends BaseClass {

	@FindBy(xpath="//*[@id=\"root\"]")
	public WebElement onsite;
	
	@FindBy(xpath="(//*[@class=\"chNavText darkGreyText\"])[2]")
	public WebElement hotels;
	
	@FindBy(xpath="//*[@id='city']")
	public WebElement destopt;

	@FindBy(xpath="//*[@class='react-autosuggest__input react-autosuggest__input--open']")
	public WebElement destination;

	@FindBy(xpath="(//*[@class='font12 greyText appendBottom5'])")
	public WebElement selectoption;
	
	@FindBy(xpath="//*[@id='checkin']")
	public WebElement chkinopt;

	@FindBy(xpath="//*[@id='checkout']")
	public WebElement chkoutopt;
	
	@FindBy(xpath="(//div[@class='DayPicker-Day' and contains(string(),\"25\")])")
	public WebElement chckindate;
	
	@FindBy(xpath="(//div[@class='DayPicker-Day' and contains(string(),\"26\")])")
	public WebElement chckoutdate;

	@FindBy(xpath="//*[@id='guest']")
	public WebElement guestopt;

	@FindBy(xpath="//*[@data-cy='adults-1']")
	public WebElement numberofguests;
	
	@FindBy(xpath="//*[@data-cy='submitGuest']")
	public WebElement Submitguest;
	
@FindBy(xpath="//*[@data-cy='travelForText']")
public WebElement Travelrsn;

	@FindBy(xpath="(//*[@data-cy='travelFor-Work'])")
public WebElement reasons;
	
	@FindBy(xpath="(//*[@id='hsw_search_button'])")
	public WebElement search;
	
	public void ClickOnHotelOptions(){
	try
	{
		logger.log(Status.INFO, "Click on Hotels element option");
		onsite.click();
		waitforspecifictime(5000);
		verifyElementIsDisplayed(hotels);
		hotels.click();
		logger.log(Status.PASS, "Hotels clicked succesfully");
	}
	catch(Exception e){
		e.getMessage();
	}
	}

public void ChooseDestination(String dest){
	try{
		logger.log(Status.INFO, "Choose your destination");
	destopt.click();
	verifyElementIsDisplayed(destination);
	destination.sendKeys(dest);
	waitforspecifictime(2000);
//	destopt.sendKeys(dest);	
	verifyElementIsDisplayed(selectoption);
selectoption.click();
logger.log(Status.PASS, "Destination choosen sucessfully");
}
catch(Exception e){
	e.getMessage();
}
}

public void CheckInDate(){
	try{
		logger.log(Status.INFO, "Enter check in date");
	chkinopt.click();
	chckindate.click();
	logger.log(Status.PASS, "Check in date entered successfully");
}
catch(Exception e){
	e.getMessage();
}
}

public void CheckOutDate(){
	try{
		logger.log(Status.INFO, "Enter check out date");
	chkoutopt.click();
	chckoutdate.click();
	logger.log(Status.PASS, "Check out date entered successfully");
}
catch(Exception e){
	e.getMessage();
}
}

public void NumberofGuests(){
	try{
		logger.log(Status.INFO, "Enter number of guests");
	guestopt.click();
numberofguests.click();
Submitguest.click();
logger.log(Status.PASS, "Number of guests entered succesfully");
}
catch(Exception e){
	e.getMessage();
}
}

public void TravellingReason(){
	try{
		logger.log(Status.INFO, "Enter reason of travelling");
	Travelrsn.click();	
reasons.click();
logger.log(Status.PASS, "Reason of travelling entered");
}
catch(Exception e){
	e.getMessage();
}
}

public BookingHotel HotelBooking(){
try{
	logger.log(Status.INFO, "Search for Hotels");
	search.click();
	logger.log(Status.PASS, "Search successful for hotels");
	
}
catch(Exception e){
	e.getMessage();
}
return PageFactory.initElements(driver, BookingHotel.class);
}
}
