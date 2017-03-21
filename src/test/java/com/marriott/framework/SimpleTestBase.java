package com.marriott.framework;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SimpleTestBase
{
	protected String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	protected String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	protected String SAUCE_URL = "https://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:443/wd/hub";
	
	protected String SELENIUM_BROWSER = System.getenv("SELENIUM_BROWSER");
	protected String SELENIUM_VERSION = System.getenv("SELENIUM_VERSION");
	protected String SELENIUM_PLATFORM = System.getenv("SELENIUM_PLATFORM");
	
	protected RemoteWebDriver driver;
	protected String sessionId;
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before() throws Exception
	{
		URL url = getWebdriverUrl();
		System.out.println("url: " + url);
		
		DesiredCapabilities capabilities = getDesiredCapabilies();
		System.out.println("capabilities: " + capabilities);
		
		driver = new RemoteWebDriver(url, capabilities);
		sessionId = driver.getSessionId().toString();
		System.out.println("driver: " + driver);
		System.out.println("sessionId: " + sessionId);
	}
	
	@After
	public void after() throws Exception
	{
		driver.quit();
	}
	
	public URL getWebdriverUrl() throws Exception
	{
		if (SAUCE_USERNAME == null) { throw new Exception("SAUCE_USERNAME must be set"); }
		if (SAUCE_ACCESS_KEY == null) { throw new Exception("SAUCE_ACCESS_KEY must be set"); }
		
		SAUCE_URL = SAUCE_URL
				.replace("SAUCE_USERNAME", SAUCE_USERNAME)
				.replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);
		
		return new URL(SAUCE_URL);
	}
	
	public DesiredCapabilities getDesiredCapabilies()
	{
		if (SELENIUM_BROWSER == null) { SELENIUM_BROWSER = "chrome"; }
		if (SELENIUM_VERSION == null) { SELENIUM_VERSION = "latest"; }
		if (SELENIUM_PLATFORM == null) { SELENIUM_PLATFORM = "linux"; }
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(SELENIUM_BROWSER);
		capabilities.setVersion(SELENIUM_VERSION);
		capabilities.setCapability(CapabilityType.PLATFORM, SELENIUM_PLATFORM);
		capabilities.setCapability("name", this.getClass().getSimpleName() + " " + testName.getMethodName());
		return capabilities;
	}
}
