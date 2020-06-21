package TravelPhp.TravelPhp;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Travelphptests {
	public static WebDriver driver;
	
 
@BeforeClass
  public void beforeClass() {
	
	if(Config.browser.equalsIgnoreCase("Chrome"))
	{
	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get(Config.url);
	driver.manage().timeouts().implicitlyWait(Config.timeout,TimeUnit.SECONDS);
    driver.manage().window().maximize();
	}
    else
    {
    	driver = new FirefoxDriver();
    	driver.get(Config.url);
    	driver.manage().timeouts().implicitlyWait(Config.timeout,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    
	
	  
  }
  @Test(priority=0,description="Select filgt and Economy category")
  public void Selectflight() throws Exception {
	  WebElement Flights=driver.findElement(By.xpath("//*[@id='search']/div/div/div/div/div/nav/ul/li[2]/a"));	
	  Flights.click();
	  
	  WebElement Selectcat=driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div[1]/div[2]/div/div"));
	  Selectcat.click();
	  
	  Thread.sleep(3000);
	  WebElement Economical=driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div[1]/div[2]/div/div/div/ul/li[3]"));
	  Economical.click();
	  
  }
  
  @Test(priority=1,description="Select Roundtrip")
  public void SelectRoundtrip() {
	  WebElement RoundTrip=driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div[1]/div[1]/div/div[2]/label"));
	  RoundTrip.click();
	  
  }
  
  @Test(priority=2,description="Select from city")
  public void FromCity() {
	  WebElement Frombox=driver.findElement(By.xpath("//*[@id='s2id_location_from']"));
	  Frombox.click();
	  WebElement Fromboxinput=driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
	  Fromboxinput.sendKeys(Config.FromCity);
	  WebElement Fromelement=driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li/div"));
	  Actions action = new Actions(driver);
	  action.moveToElement(Fromelement).click().perform();
	  
  }
  @Test(priority=3,description="Select To city")
  public void ToCity() {
	  WebElement Tobox=driver.findElement(By.xpath("//*[@id='s2id_location_to']"));
	  Tobox.click();
	  WebElement Toinput=driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
	  Toinput.sendKeys(Config.Tocity);
	  WebElement Toelement=driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li/div"));
	  Actions action = new Actions(driver);
	  action.moveToElement(Toelement).click().perform();
	  
  }
  @Test(priority=4,description="Depart date")
  public static void Departdate() throws Exception
  {
	  
	  
	   WebElement ele=driver.findElement(By.xpath("//*[@id='FlightsDateStart']"));
	   String Departdate=Config.Departdate;
	   JavascriptExecutor js = (JavascriptExecutor) driver;  
	   js.executeScript("arguments[0].setAttribute('placeholder','"+Departdate+"',);", ele);
	  Thread.sleep(2000);
	  
  }
	  
  @Test(priority=5,description="Return date")
  public static void Returndate() throws Exception
  {
	  
	  
	   WebElement ele2=driver.findElement(By.xpath("//*[@id='FlightsDateEnd']"));
	   String Returndate=Config.Returndate;
	   JavascriptExecutor js = (JavascriptExecutor) driver;  
	   js.executeScript("arguments[0].setAttribute('placeholder','"+Returndate+"',);", ele2);
	  Thread.sleep(2000);
	  
  }
 
  @Test(priority=6,description="Select Adults")
  public void SelectAdults() {
	  {
			for(int i =0;i<Config.Adults;i++)
		     {
				WebElement Adults=driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div[3]/div[3]/div/div/div[1]/div/div[2]/div/span/button[1]"));

				Adults.click();
		     }
		}
	  
  }
  
  @Test(priority=7,description="Select Childs")
  public static void SelectChild()
  {
	  
  	for(int i =0;i<Config.Childs;i++)
       {
  		 WebElement Child=driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div[3]/div[3]/div/div/div[2]/div/div[2]/div/span/button[1]"));
  		Child.click();
       }
  }
  

  @Test(priority=8,description="Search Result")
  public static void SearchResult()
  {
	  WebElement Searchbutton=driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div[3]/div[4]/button"));
	  Searchbutton.click();
	  WebDriverWait wait = new WebDriverWait(driver,30);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterResultCallapseOnMobile']/div/div/div/div/h4")));
	  WebElement we=driver.findElement(By.xpath("//*[@id='filterResultCallapseOnMobile']/div/div/div/div/h4"));
	  boolean val=we.isDisplayed();
	  Assert.assertEquals(val, true);
	  
  }
  
  
  
  @AfterClass
  public void afterClass() {
	  driver.close();
	 
	 
  }

}
