package com.marriott.framework.pages;

import com.marriott.framework.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class HomePage
{
	public String path = "/default.mi";
	public Expected expected = new Expected();
	
	public class Expected
	{
		public String title = "Hotel Rooms | Find Your Perfect Hotel Room with Marriott";
	}
	
	public By searchField = By.id("search-location");
	public By fromDate = By.id("hotel-fromDate");
	public By toDate = By.id("hotel-toDate");
	public By findButton = By.cssSelector("button[title='Find']");
	
	WebDriver driver;
	Environment env;
	
	public HomePage(WebDriver driver, Environment env)
	{
		this.driver = driver;
		this.env = env;
	}
	
	public static HomePage open(WebDriver driver, Environment env)
	{
		return new HomePage(driver, env);
	}
	
	public WebElement searchField()
	{
		return driver.findElement(searchField);
	}
	
	public WebElement searchField(String text)
	{
		searchField().sendKeys(text);
		return searchField();
	}
	
	public WebElement findButton()
	{
		return driver.findElement(findButton);
	}
	
	public WebElement fromDate()
	{
		System.out.println("fromDate locator: " + fromDate);
		
		return driver.findElement(fromDate);
	}
	
	public WebElement toDate()
	{
		return driver.findElement(toDate);
	}
	
	public HotelSearch hotelSearch = new HotelSearch();
	
	public class HotelSearch
	{
		public String startDate(String date)
		{
			fromDate().clear();
			fromDate().sendKeys(date);
			return startDate();
		}
		
		public String startDate()
		{
			return fromDate().getAttribute("value");
		}
		
		public String endDate(String date)
		{
			toDate().clear();
			toDate().sendKeys(date);
			return endDate();
		}
		
		public String endDate()
		{
			return toDate().getAttribute("value");
		}
	}
	
	
	public ExpectedCondition<Boolean> isLoaded()
	{
		return new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				if (! driver.getTitle().equals(expected.title)) { return false; }
				if (! driver.getCurrentUrl().contains(path)) { return false ; }
				if (driver.findElements(searchField).size() != 1 ) { return false; }
				
				return true;
			}
		};
	}
}
