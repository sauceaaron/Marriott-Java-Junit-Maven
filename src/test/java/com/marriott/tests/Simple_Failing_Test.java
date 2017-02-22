package com.marriott.tests;

import com.marriott.framework.SauceTestBase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Simple_Failing_Test extends SauceTestBase
{
	public Simple_Failing_Test(String os, String version, String browser, String deviceName, String deviceOrientation)
	{
		super(os, version, browser, deviceName, deviceOrientation);
	}
	
	@Test
	public void fails()
	{
		driver.get("https://saucelabs.com");
		assertThat(driver.getTitle()).contains("Marriott");
	}
}
