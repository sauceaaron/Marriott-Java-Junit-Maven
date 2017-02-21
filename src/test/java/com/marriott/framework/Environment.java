package com.marriott.framework;

public class Environment
{
	public String baseUrl;
	
	public static Environment Production = new Environment()
	{{
		baseUrl = "http://www.marriott.com";
	}};
}
