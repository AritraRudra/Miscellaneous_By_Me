package datetime.demo;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import datetime.DateTimeStringConversion;

public class Java8DateTimeDemo {

	private static final String CUSTOM_DATE_FORMAT = "dd-MMM-yyyy";
	private static final String CUSTOM_DATE_TIME_FORMAT = "dd-MMM-yyyy HH:mm:ss";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMAT);
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(CUSTOM_DATE_TIME_FORMAT);
			
	public static void main(String[] args){
		final String dateStr = "15-Aug-2017";
		final String dateTimeStr = "15-Aug-2017 10:20:30";
		final String dateFormat = "dd-MMM-yyyy";
		final String dateTimeFormat = "dd-MMM-yyyy HH:mm:ss";
		
		try {
			final Date date = DateTimeStringConversion.parseUtilDateFromString(dateTimeStr, null);
			
			System.out.println(DateTimeStringConversion.convertFromUtilDateToStringPriorToJava8(date, null));

			System.out.println(DateTimeStringConversion.convertFromUtilDateToStringPriorToJava8(date, dateFormat));
			System.out.println(DateTimeStringConversion.convertFromUtilDateToStringPriorToJava8(date, dateTimeFormat));
			
			System.out.println(DateTimeStringConversion.convertFromUtilDateToString(date, null));
			System.out.println(DateTimeStringConversion.convertFromUtilDateToString(date, dateFormat));
			System.out.println(DateTimeStringConversion.convertFromUtilDateToString(date, dateTimeFormat));
			
			//System.out.println(parseUtilDateFromStringPriorToJava8(dateStr, null));
			System.out.println(DateTimeStringConversion.parseUtilDateFromStringPriorToJava8(dateTimeStr, null));
			System.out.println(DateTimeStringConversion.parseUtilDateFromStringPriorToJava8(dateStr, dateFormat));
			//System.out.println(parseUtilDateFromStringPriorToJava8(dateStr, dateTimeFormat));
			System.out.println(DateTimeStringConversion.parseUtilDateFromStringPriorToJava8(dateTimeStr, dateFormat));
			System.out.println(DateTimeStringConversion.parseUtilDateFromStringPriorToJava8(dateTimeStr, dateTimeFormat));

			//System.out.println(parseUtilDateFromString(dateStr, null));
			//TODO //System.out.println(parseUtilDateFromString(dateStr, dateFormat));
			//System.out.println(parseUtilDateFromString(dateStr, dateTimeFormat));
			System.out.println(DateTimeStringConversion.parseUtilDateFromString(dateTimeStr, null));
			//System.out.println(parseUtilDateFromString(dateTimeStr, dateFormat));
			System.out.println(DateTimeStringConversion.parseUtilDateFromString(dateTimeStr, dateTimeFormat));
			
			System.out.println(DateTimeStringConversion.parseLocalDateFromString(dateStr, null));
			//System.out.println(parseLocalDateFromString(dateTimeStr, null));
			System.out.println(DateTimeStringConversion.parseLocalDateFromString(dateStr, dateFormat));
			System.out.println(DateTimeStringConversion.parseLocalDateFromString(dateTimeStr, dateTimeFormat));
			//System.out.println(parseLocalDateTimeFromString(dateStr, null));
			//System.out.println(parseLocalDateTimeFromString(dateStr, dateFormat));
			System.out.println(DateTimeStringConversion.parseLocalDateTimeFromString(dateTimeStr, null));
			System.out.println(DateTimeStringConversion.parseLocalDateTimeFromString(dateTimeStr, dateTimeFormat));
			
			System.out.println(DateTimeStringConversion.getLocalDateFromUtilDate(date));
			System.out.println(DateTimeStringConversion.getLocalDateTimeFromUtilDate(date));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	

}
