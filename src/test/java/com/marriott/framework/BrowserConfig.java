package com.marriott.framework;

import java.util.LinkedList;

public class BrowserConfig
{
	public static LinkedList getBrowsers()
	{
		LinkedList browsers = new LinkedList();
		
		browsers.add(new String[]{"Windows 10", "14.14393", "MicrosoftEdge", null, null});
		browsers.add(new String[]{"Windows 10", "49.0", "firefox", null, null});
		browsers.add(new String[]{"Windows 7", "11.0", "internet explorer", null, null});
		browsers.add(new String[]{"OS X 10.11", "10.0", "safari", null, null});
		browsers.add(new String[]{"OS X 10.10", "54.0", "chrome", null, null});
		browsers.add(new String[]{"Linux", "45.0", "firefox", null, null});
		browsers.add(new String[]{"Linux", "48.0", "chrome", null, null});
		
		return browsers;
	}
}