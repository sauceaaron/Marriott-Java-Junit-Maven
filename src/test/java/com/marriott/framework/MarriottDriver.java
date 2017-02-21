package com.marriott.framework;

import com.marriott.framework.pages.*;
import org.openqa.selenium.WebDriver;

public class MarriottDriver
{
	WebDriver driver;
	Environment env;
	
	public HomePage homePage;
	public FindHotelsPage findHotelsPage;
	
	public MarriottDriver(WebDriver driver, Environment env)
	{
		initializePages(driver, env);
	}
	
	public void initializePages(WebDriver driver, Environment env)
	{
		this.driver = driver;
		this.env = env;
		
		homePage = new HomePage(driver, env);
		findHotelsPage = new FindHotelsPage(driver, env);
	}
	
	public void open()
	{
		driver.get(env.baseUrl);
	}
	
	public String title()
	{
		return driver.getTitle();
	}
		
}
