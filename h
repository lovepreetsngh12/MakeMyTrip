DriverClass

public class DriverClass {



 public static WebDriver driver;



 public static WebDriver driverInstance;







 //Method to instantiate the driver



 public static void driverInstantiation(String browser) throws Exception{



 //Check if parameter passed as 'chrome'



 if(browser.equalsIgnoreCase("chrome")){



 //set path to chromedriver.exe



 System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");



 //create chrome instance



 driver = new ChromeDriver();



 }



 //Check if parameter passed as 'Edge'



 else if(browser.equalsIgnoreCase("Edge")){



 //set path to Edge.exe



  System.setProperty("webdriver.edge.driver",".\\drivers\\MicrosoftWebDriver.exe");



 //create Edge instance



  driver = new EdgeDriver();



 }



 else{



 //If no browser passed throw exception



 throw new Exception("Browser is not correct");



 }







 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



 driver.manage().window().maximize();







 }











 //Method to create the driver instance and flow through the whole code



 public static WebDriver driverInstance(){



 driverInstance = driver;



 return driverInstance;



 }







}



=========================================================================================================================================



testcase:

public class TC001 {







 public static WebDriver driver;



 public static WebDriverWait wait;







 /**



 * This function will execute before each Test tag in testng.xml



 * @param browser



 * @throws Exception



 */



 @BeforeTest



 @Parameters("browser")



 public void setUp(String browser) throws Exception {



 DriverClass.driverInstantiation(browser);



 driver = DriverClass.driverInstance();



 wait = new WebDriverWait(driver, 30);



 String log4jConfPath = "log4j.properties";



 PropertyConfigurator.configure(log4jConfPath);



 }







 @Test



 public void TC001_RedBus() {



 Log.startTestCase("TC001_RedBus");



 driver.get("https://www.redbus.in/");



 Log.info("URL opened successfully");



 }







 @AfterTest



 public void afterClass() {



 }







}

==========================================================================================================================================



CommonActions

public class CommonActions {



 public static WebDriver driver = DriverClass.driverInstance();







 //Final method for switching to a new tab



 public static final void switchingControlToaNewWindow(int windowNo){



 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());



 driver.switchTo().window(tabs.get(windowNo));



 }







 //Taking the ScreenShot



 public static void capture() throws IOException {



 File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);



 File Dest = new File("src/../ErrImages/" + System.currentTimeMillis() + ".png");



 FileUtils.copyFile(scrFile, Dest);



 }







}

==========================================================================================================================================



ExcelOutputInput

public class ExcelOutputInput {







 public static XSSFWorkbook excelWorkbook;



 public static XSSFSheet sheet;



 public static XSSFRow row;



 public static XSSFCell cell;







 //Setting the excel to perform input and output



 public static void setExcel(String path, String sheetName) throws IOException{



 FileInputStream excelFile = new FileInputStream(path);



 excelWorkbook = new XSSFWorkbook(excelFile);



 sheet = excelWorkbook.getSheet(sheetName);



 }







 //Method to retrieve cell data using row number and column number for index



 public static String getCellData(int rowNum, int colNum){



 return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();



 }







 //This method will create a cell and write the data in it



 public static void setCellData(String result, int rowNum, int colNum) throws IOException{



 row = sheet.getRow(rowNum);



 cell = row.createCell(colNum);



 cell.setCellValue(result);







 FileOutputStream fileOut = new FileOutputStream("testData\\Test_Data.xlsx");



 excelWorkbook.write(fileOut);



 fileOut.flush();



 fileOut.close();



 }



}





==========================================================================================================================================



testNG.xml

<?xml version="1.0" encoding="UTF-8"?>



<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">



<suite name="TestSuite" thread-count="2" parallel="tests" >







<test name="ChromeTest">



<parameter name="browser" value="Chrome" />



<classes>



<class name="testcases.TC001">



</class>



</classes>



</test>







<test name="EdgeTest">



<parameter name="browser" value="Edge" />



<classes>



<class name="testcases.TC001">



</class>



</classes>



</test>







</suite>

==========================================================================================================================================



pageObj:



WebDriver driver;







 public Adyen_Page(WebDriver driver) {



 this.driver = driver;



 PageFactory.initElements(driver, this);



 }







 @FindBy(id = "accountTextInput")



 public WebElement accountName;



==========================================================================================================================================System.setProperty("webdriver.gecko.driver", "D:\\\\ToolsQA\\trunk\\Library\\drivers\\geckodriver.exe");



 WebDriver driver = new FirefoxDriver();



 driver.get("http://www.toolsqa.com");



==========================================================================================================================================



log4j.xml:



<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">



<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" debug="false">



<appender name="fileAppender" class="org.apache.log4j.FileAppender">



<param name="Threshold" value="INFO" />



<param name="File" value="logfile.log"/>



<layout class="org.apache.log4j.PatternLayout">



<param name="ConversionPattern" value="%d %-5p [%c{1}] %m %n" />



</layout>



</appender>



<root>



<level value="INFO"/>



<appender-ref ref="fileAppender"/>



</root>



</log4j:configuration>

==========================================================================================================================================



log4j.properties:



log4j.rootLogger=INFO, stdout



log4j.appender.stdout=org.apache.log4j.ConsoleAppender



log4j.appender.stdout.Target=System.out



log4j.appender.stdout.layout=org.apache.log4j.PatternLayout



log4j.appender.stdout.layout.ConversionPattern=%d{yy/MM/dd HH:mm:ss} %p %c{2}: %m%n

==========================================================================================================================================



Log Class:



// Initialize Log4j logs



 private static Logger Log = Logger.getLogger(Log.class.getName());//







 // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite



 public static void startTestCase(String sTestCaseName){



 Log.info("****************************************************************************************");



 Log.info("****************************************************************************************");



 Log.info("$$$$$$$$$$$$$$$$$$$$$ "+sTestCaseName+ " $$$$$$$$$$$$$$$$$$$$$$$$$");



 Log.info("****************************************************************************************");



 Log.info("****************************************************************************************");



 }







 //This is to print log for the ending of the test case



 public static void endTestCase(String sTestCaseName){



 Log.info("XXXXXXXXXXXXXXXXXXXXXXX "+"-E---N---D-"+" XXXXXXXXXXXXXXXXXXXXXX");



 Log.info("X");



 Log.info("X");



 Log.info("X");



 Log.info("X");



 }







 // Need to create these methods, so that they can be called



 public static void info(String message) {



 Log.info(message);



 }







 public static void warn(String message) {



  Log.warn(message);



 }







 public static void error(String message) {



  Log.error(message);



 }







 public static void fatal(String message) {



  Log.fatal(message);



 }







 public static void debug(String message) {



  Log.debug(message);



 }

==========================================================================================================================================
