package com.marriott.tests;

import com.marriott.framework.SimpleTestBase;
import org.junit.Test;

public class Simple_Test_With_No_Status extends SimpleTestBase
{
	@Test
	public void test()
	{
		driver.get("https://saucelabs.com");
	}
}
