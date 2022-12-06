package com.utility;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Keywords {

	public WebDriver driver;
	sparklogs s;
	SoftAssert softAssert;
	public Keywords(WebDriver driver, sparklogs s) {
		this.softAssert=null;
		this.driver=driver;
		this.s=s;
		// TODO Auto-generated constructor stub
	}
	public Keywords(sparklogs s) {
		this.softAssert=null;
		this.driver=null;
		this.s=s;
		// TODO Auto-generated constructor stub
	}
	public Keywords(WebDriver driver, sparklogs s, SoftAssert softAssert) {
		this.softAssert=softAssert;
		this.driver=driver;
		this.s=s;
		// TODO Auto-generated constructor stub
	}
	public void click(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	}
	public void sendkeys(String xpath, String data) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).sendKeys(data);
	}
	public WebDriver Open() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(driver);
		return driver;
	}
	public boolean Check_for_presence_of_Element( String xpath) {
		//s.info("Check for Presen");
		List<WebElement> p= driver.findElements(By.xpath(xpath));
		if (p.isEmpty()) {
			System.out.println("element absence");
			return false;
		}
		else {
			//driver.findElement(By.xpath(xpath));
			//s.pass("Element Present "+xpath);
			return true;
		}

	}

	public String getdata(String xpath) {
		//System.out.println("getdata");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String data =driver.findElement(By.xpath(xpath)).getText();	
		//System.out.println("Actual"+data);
		return data;
	}
	public void check_data(String xpath, String Expected_data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		if(getdata(xpath).toLowerCase().equals(Expected_data.toLowerCase())) {
			s.pass("Data Matched");
			if(!getdata(xpath).equals(Expected_data)) {
				s.warning("Expected data "+Expected_data+ " Actual data "+getdata(xpath),new Screenshot().take_screenshoot(driver));
			}
		}
		else {
			s.fail("Expected data "+Expected_data+ " Actual data "+getdata(xpath),new Screenshot().take_screenshoot(driver));
			softAssert.fail("Expected data "+Expected_data+ " Actual data "+getdata(xpath));
			//	softAssert.assertEquals(getdata(driver,xpath), Expected_data,"Error Encountered ");

			//	log.info(getdata(driver,xpath)+" "+ Expected_data+" Error Encountered ");
			//	System.out.println("Check data"+getdata(driver,xpath)+" "+Expected_data  );

		}

	}


}
