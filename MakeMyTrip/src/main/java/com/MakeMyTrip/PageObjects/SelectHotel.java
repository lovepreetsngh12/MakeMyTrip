package com.MakeMyTrip.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.MakeMyTrip.BaseObjects.BaseClass;
import com.MakeMyTrip.BusinessComponents.ReadExcelDataFile;
import com.aventstack.extentreports.Status;

public class SelectHotel extends BaseClass{
	
	@FindBy(xpath="//*[@id=\"hlistpg_hotel_name\"]")
public WebElement hotelnameslist;
	
	@FindBy(xpath="//*[@id=\"hlistpg_hotel_shown_price\"]")
	public WebElement hotelpricelist;
	
	@FindBy(xpath="(//*[@id=\"Listing_hotel_0\"])[1]")
	public WebElement hotel;
	
	
	public void GetListofNameandPrice(String sheetname){
		try{
			logger.log(Status.INFO, "Getting list of hotels with respective price");
			List<WebElement> listofname = driver.findElements(By.xpath("//*[@id=\"hlistpg_hotel_name\"]"));
		List<WebElement> listofprice = driver.findElements(By.xpath("//*[@id=\"hlistpg_hotel_shown_price\"]"));
		String[] HotelNames = new String[5];
		String[] HotelPrice = new String[5];
		for (int i = 0; i < 5; i++) {
			HotelNames[i] = listofname.get(i).getText();
			HotelPrice[i] = listofprice.get(i).getText();
			System.out.println(HotelNames[i] + ":" + HotelPrice[i]);
		}
		ReadExcelDataFile data = new ReadExcelDataFile(
					System.getProperty("user.dir") + "\\src\\main\\java\\Resources.Excelsheets\\hs.xlsx");
			if (data.isSheetExist(sheetname) == false) {
				data.addSheet(sheetname);
			}
			data.addColumn(sheetname, "Name of Hotel");
			data.addColumn(sheetname, "Price of Hotel per Night");
			for (int x = 0; x < 5; x++) {
				data.setCellData(sheetname, "Name of Hotel", x + 2, HotelNames[x]);
				data.setCellData(sheetname, "Price of Hotel per Night", x + 2, HotelPrice[x]);
			}
			logger.log(Status.PASS, "List added successfully");
			}
		catch(Exception e){
			reportFail(e.getMessage());
		}
			
		}
		
	
	
	public void BookTopHotel(){
		try{
			logger.log(Status.INFO,"Clicking on the top hotel");
		verifyElementIsDisplayed(hotel);
		hotel.click();
		getTitle("MakeMyTrip");
			waitforspecifictime(2000);
logger.log(Status.PASS, "Top hotel checked");
		}
	catch(Exception e){
		reportFail(e.getMessage());
		
	}	
	}
	

}
