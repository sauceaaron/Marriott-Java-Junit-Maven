package com.marriott.tests;

import com.marriott.framework.*;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.InvalidElementStateException;

import static org.assertj.core.api.Assertions.assertThat;

public class Search_for_hotel extends SauceTestBase
{
	MarriottDriver marriott;
	
	public Search_for_hotel(String os, String version, String browser, String deviceName, String deviceOrientation)
    {
            super(os, version, browser, deviceName, deviceOrientation);
    }
	
    @Before
    public void setup()
    {
	    marriott = new MarriottDriver(driver, Environment.Production);
    }
    
    @Test
    public void on_home_page() throws InvalidElementStateException
    {
	    marriott.open();
	    wait.until(marriott.homePage.isLoaded());
	    
	    assertThat(driver.getTitle()).isEqualTo(marriott.homePage.expected.title);
    }
	
	@Test
	public void start_date_should_be_today() throws Exception
	{
		marriott.open();
		wait.until(marriott.homePage.isLoaded());
		
		String startDate = marriott.homePage.fromDate().getAttribute("value") ;
		
		String today = DateUtil.today().format();
		assertThat(startDate).startsWith(today);
	}
	
	@Test
	public void end_date_should_be_tomorrow() throws Exception
	{
		marriott.open();
		wait.until(marriott.homePage.isLoaded());
		
		String endDate = marriott.homePage.toDate().getAttribute("value");
		String tomorrow = DateUtil.tomorrow().format();
		
		System.out.println("got end date: " + endDate);
		System.out.println("expected end date: " + tomorrow);
		
		assertThat(endDate).isEqualTo(tomorrow);
	}
	
	@Test
	public void by_landmark() throws Exception
	{
		marriott.open();
		wait.until(marriott.homePage.isLoaded());
		
		marriott.homePage.searchField("Statue of Liberty");
		marriott.homePage.findButton().click();
		
		wait.until(marriott.findHotelsPage.isLoaded());
	}

	@Test
	public void by_city() throws Exception
	{
		String searchCriteria = "Seattle";
		
		marriott.open();
		wait.until(marriott.homePage.isLoaded());
		
		marriott.homePage.searchField(searchCriteria);
		marriott.homePage.findButton().click();
		
		wait.until(marriott.findHotelsPage.isLoaded());
	}

	@Test
	public void by_airport() throws Exception
	{
		String searchCriteria = "LAX";
		
		marriott.open();
		wait.until(marriott.homePage.isLoaded());
		
		marriott.homePage.searchField(searchCriteria);
		marriott.homePage.findButton().click();
		
		wait.until(marriott.findHotelsPage.isLoaded());
		
		assertThat(marriott.findHotelsPage.numberOfResults()).isGreaterThan(1);
		assertThat(marriott.findHotelsPage.searchCriteria()).containsIgnoringCase(searchCriteria);
	}
	
	@Test
	public void should_show_available_hotels() throws Exception
	{
		String startDate = DateUtil.tomorrow().toString();
		String endDate = DateUtil.tomorrow().addDays(7).toString();
		String searchCriteria = "Statue of Liberty";
		
		marriott.open();
		
		wait.until(marriott.homePage.isLoaded());
		
		marriott.homePage.searchField(searchCriteria);
		marriott.homePage.hotelSearch.startDate(startDate);
		marriott.homePage.hotelSearch.endDate(endDate);
		marriott.homePage.findButton().click();
		
		wait.until(marriott.findHotelsPage.isLoaded());
	
		assertThat(marriott.findHotelsPage.numberOfResults()).isGreaterThan(1);
		assertThat(marriott.findHotelsPage.searchCriteria()).containsIgnoringCase(searchCriteria);
	}
}