package com.KAMAN.main;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.KAMAN.pages.LoginPage;
import com.KAMAN.lib.ExcelApiTest;;

public class Main {

	String currentDirectory = System.getProperty("user.dir");
	String xlFilePath = currentDirectory +"\\KAMAN_DATA_Git.xlsx";
    int sheet = 0;
    ExcelApiTest eat = null;
    LoginPage login;
    WebDriver driver;
    WebDriverWait wait;
    
    String URL="https://ectest.kamandirect.com/storeus/myaccount/login.jsp";
    String profile_URL = "https://ectest.kamandirect.com/storeus/myaccount/profile.jsp";
	
      @BeforeTest
    public void setUp() throws InterruptedException {
    	System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
    	driver = new ChromeDriver();
    	wait = new WebDriverWait(driver, 10);
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.get(URL);	
    	login= new LoginPage(driver);
    	Thread.sleep(2000);
    }
    
    @Test(priority=1 , dataProvider = "userData")
    public void Login_Kaman(String Name, String Pass) throws Exception {
    	login.KAMAN_login(Name,Pass);
    	System.out.println("From Branch A");
    	Thread.sleep(2000);
    	
    }
    
    
    @AfterTest
    public void logout() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.navigate().to(profile_URL);
    	Thread.sleep(2000);
    }
    
    @AfterSuite
    public void closeDriver() throws IOException {
    	Runtime rt = Runtime.getRuntime();
        rt.exec("taskkill /F /IM chromedriver.exe");
        driver.close();
    }
    
  @DataProvider(name="userData")
  	public Object[][] userFormData() throws Exception
  {
      Object[][] data = testData(xlFilePath, sheet);
      return data;
  }
   
  	public Object[][] testData(String xlFilePath, int sheet) throws Exception
  {
      Object[][] excelData = null;
      eat = new ExcelApiTest(xlFilePath);
      int rows = eat.RowCount(sheet);
      int columns = eat.ColCount(sheet);
               
      excelData = new Object[rows-1][columns];
      
      for(int i=1; i<rows; i++)
      {
          for(int j=0; j<columns; j++)
          {
              excelData[i-1][j] = eat.getData(i,j,sheet);
          }  
      }
      return excelData;
  }
}

