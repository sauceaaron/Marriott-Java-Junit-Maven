package com.marriott.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	static SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d");
	static Calendar calendar = Calendar.getInstance();
	
	public DateUtil()
	{
		calendar.setTime(new Date());
	}
	
	public DateUtil(Date date)
	{
		calendar.setTime(date);
	}
	
	public static DateUtil today()
	{
		return new DateUtil();
	}
	
	public static DateUtil tomorrow() throws ParseException
	{
		return today().addDays(1);
	}
	
	public static DateUtil yesterday() throws ParseException
	{
		return today().subtractDays(1);
	}
	
	public String format()
	{
		return dateFormatter.format(calendar.getTime());
	}
	
	public DateUtil addDays(int days) throws ParseException
	{
		calendar.add(Calendar.DATE, days);
		
		return this;
	}
	
	public DateUtil subtractDays(int days) throws ParseException
	{
		calendar.add(Calendar.DATE, days * -1);
		
		return this;
	}
	
	public String toString()
	{
		return format();
	}
}
