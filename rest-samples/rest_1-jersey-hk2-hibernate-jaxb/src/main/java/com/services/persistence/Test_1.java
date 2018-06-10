package com.services.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test_1 {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
		System.out.println(dateFormatter.parse("15-Aug-2017"));
		
		Date date = dateFormatter.parse("15-Aug-2017");
		 
		//Java 8 datetime LocalDate
		LocalDate java8date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		java8date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		System.out.println(java8date);
		
		LocalDate fromCustomPattern = LocalDate.parse("15-Aug-2017", DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		System.out.println(fromCustomPattern);
		System.out.println(java8date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		
		
		String[] thatArr;
		String str = "ABthatCDthatBHthatIOthatoo";
		thatArr = str.split("that");
		System.out.println(thatArr.length-1);
		thatArr = str.split("that",-1);
		System.out.println(thatArr.length-1);
		
		str = "ABthatCDthatBHthatIOthat";
		thatArr = str.split("that");
		System.out.println(thatArr.length-1);
		thatArr = str.split("that",-1);
		System.out.println(thatArr.length-1);
	}

}
