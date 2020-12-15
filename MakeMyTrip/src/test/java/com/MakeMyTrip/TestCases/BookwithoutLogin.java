package com.MakeMyTrip.TestCases;

import java.util.Hashtable;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.MakeMyTrip.BaseObjects.BaseClass;
import com.MakeMyTrip.PageObjects.BookingHotel;
import com.MakeMyTrip.PageObjects.LandingPage;
import com.MakeMyTrip.PageObjects.SearchHotels;
import com.MakeMyTrip.PageObjects.SelectHotel;

import com.MakeMyTrip.BusinessComponents.*;;

public class BookwithoutLogin extends BaseClass {
	 LandingPage lp;
	SearchHotels lf;	
	 BookingHotel bh;	
	 SelectHotel sh;
	@Test(dataProvider="DataRequiredForTesting")	
	public void SearchProductAutomatically(Hashtable<String,String> table){
			logger= report.createTest(table.get("Comments"));
			BaseClass b  =new BaseClass();
			b.InvokeBrowser("browser");
			lp=b.OpenApplication("Url");
		//	b.getTitle("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
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
