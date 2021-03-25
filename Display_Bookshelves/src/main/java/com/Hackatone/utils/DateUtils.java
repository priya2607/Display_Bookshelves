package com.Hackatone.utils;

import java.util.Date;

public class DateUtils {
	
	public static String getTimeStamp(){
		Date date = new Date();
		return (String)date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}
}
