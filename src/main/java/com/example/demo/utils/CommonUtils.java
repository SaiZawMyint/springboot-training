package com.example.demo.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	private static final DecimalFormat formatter = new DecimalFormat("#,###.##");
	
	public static Date stringToDate(String dateTime, String standardDateInputFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(standardDateInputFormat);
		Date retDate = new Date();
		try {
			retDate = sdf.parse(dateTime);
		} catch (ParseException e) {
			System.out.println("Error : " + e.getMessage());
		}
		return retDate;
	}

	public static String dateToString(Date dateTime, String standardDateInputFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(standardDateInputFormat);
		String retDate = sdf.format(dateTime);
		return retDate;
	}
	
	
	public static String formatNumber(Number number) {
		if(number == null) return "";
		return formatter.format(number);
	}
	
}
