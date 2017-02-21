package com.marriott.framework.pages;

import com.marriott.framework.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class FindHotelsPage
{
	public String path = "/search/findHotels.mi";
	public Expected expected = new Expected();
	
	WebDriver driver;
	Environment env;
	
	public class Expected
	{
		public String title = "The official site for hotel reservations for Marriott hotels and beach resorts at Marriott.com";
	}
	
	public By resultsHeading = By.cssSelector("#choose-hotels-brands");
	public By numberOfResults = By.cssSelector("#choose-hotels-brands > strong");
	public By searchCriteria = By.cssSelector("#choose-hotels-brands > span > strong");
	
	
	public FindHotelsPage(WebDriver driver, Environment env)
	{
		this.driver = driver;
		this.env = env;
	}
	
	public void open()
	{
		this.driver.get(env.baseUrl + path);
	}
	
	public int numberOfResults() {
		String results = driver.findElement(numberOfResults).getText();
		System.out.println("============results: " + results);
		int number = Integer.parseInt(results.replaceAll("[\\D]", ""));
		System.out.println("============number: " + number);
		
		return number;
	}
	
	public String searchCriteria() {
		String searchCritiera = driver.findElement(searchCriteria).getText();
		System.out.println("============searchCriteria: " + searchCritiera);
		
		return searchCritiera;
	}
	
	public ExpectedCondition isLoaded()
	{
		return new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				if (! driver.getTitle().equals(expected.title)) { return false; }
				if (! driver.getCurrentUrl().contains(path)) { return false ; }
				if (driver.findElement(resultsHeading) == null ) { return false; }
				
				return true;
			}
		};
	}
}