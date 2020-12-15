package com.MakeMyTrip.BaseObjects;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.MakeMyTrip.BusinessComponents.DateUtils;
import com.MakeMyTrip.BusinessComponents.ExtentReportManager;
import com.MakeMyTrip.PageObjects.LandingPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports report = ExtentReportManager.getReportInstance();
	public  static ExtentTest logger;
	public String browser = null;
	
	
	public void InvokeBrowser(String browserNameKey) {
		if (prop == null) {
			prop = new Properties();
			FileInputStream file;
			try {
				file = new FileInputStream(System.getProperty("user.dir")
						+ "//src//main//java//Resources//ConfigFiles//GlobalVariable.properties");
				prop.load(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//reportFail(e.getMessage());
				e.printStackTrace();
			}

		}

		try {
			if (prop.getProperty(browserNameKey).equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}

			else if (prop.getProperty(browserNameKey).equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}

		} catch (Exception e) {
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

	}
	
	public LandingPage OpenApplication(String websiteURLKey) {
	logger.log(Status.INFO, "Going to MakeMyTrip");
		driver.get(prop.getProperty(websiteURLKey));
	logger.log(Status.PASS, "MakeMyTrip Website Opened");
		return PageFactory.initElements(driver, LandingPage.class);
	}

	public void waitforspecifictime(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void explicitWait(WebElement we) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(we));
			logger.log(Status.INFO, "Elements are Visible ");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void takeScreenShot() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\Resources.Screenshots\\"
				+ DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destinationFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\src\\main\\java\\Resources.Screenshots\\"
					+ DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getTitle(String expected) {
		try {
			AssertJUnit.assertEquals(driver.getTitle(), expected);
			reportPass("Expected:" + expected);
			reportPass("Actual:" + driver.getTitle());
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void verifyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Element is displayed");
			} else {
				reportFail("Element is displayed");
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void selectDropDownValue(WebElement webElement, String value) {
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	//public void JsExecutorOptions(String script){
	//	JavascriptExecutor js = (JavascriptExecutor)driver;
	//	js.executeAsyncScript(script);
		
	//}

	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShot();
		Assert.fail(reportString);

	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	public void flushReport() {
		report.flush();
	}
	
	public void QuitBrowser(){
		driver.quit();
	}
	
}