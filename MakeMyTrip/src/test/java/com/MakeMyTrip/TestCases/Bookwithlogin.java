package com.MakeMyTrip.TestCases;

	import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.MakeMyTrip.BaseObjects.*;
import com.MakeMyTrip.BusinessComponents.TestDataProvider;
import com.MakeMyTrip.PageObjects.*;
	//import com.MakeMyTrip.BusinessComponents.*;
	public class Bookwithlogin extends BaseClass {
		static LandingPage lp;
	static SearchHotels lf;	
	static BookingHotel bh;	
	SelectHotel sh;
	@Test(dataProvider="DataRequiredForTesting")	
	public void SearchProductAutomatically(Hashtable<String,String> table){
			logger= report.createTest(table.get("Comments"));

			BaseClass b  =new BaseClass();
			b.InvokeBrowser("browser");
			lp=b.OpenApplication("Url");
	//b.getTitle("trivago.com - Compare hotel prices worldwide");
	lp.LoginForm();
	lp.EnterEmailid("email");
	lp.EnterPassword("pass");
	lp.VerifyingOTP();
	lf=lp.BookingHotelPage();
lf.ClickOnHotelOptions();
lf.ChooseDestination(table.get("Place"));
lf.CheckInDate();
lf.CheckOutDate();
lf.NumberofGuests();
lf.TravellingReason();
bh=lf.HotelBooking();
bh.ChooseOptions();
bh.GreatDeals();
bh.FreeCancellation();
sh=bh.HotelSelection();
sh.GetListofNameandPrice(table.get("sheetname"));
sh.BookTopHotel();
b.takeScreenShot();
b.flushReport();
b.QuitBrowser();
}
	
	@DataProvider
	public Object[][] DataRequiredForTesting(){
		return TestDataProvider.getTestData("hs.xlsx","Data" , "MakeMyTrip Automation");
		
	}
	}
