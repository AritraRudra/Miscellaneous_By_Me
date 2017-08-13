package datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Java8DateTimeDemo {

	public static void main(String[] args) throws ParseException {
		final String CUSTOM_DATE_FORMAT = "dd-MMM-yyyy";
		final String dateStr = "15-Aug-2017";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(CUSTOM_DATE_FORMAT);
		System.out.println(dateFormatter.parse(dateStr));
		
		Date date = dateFormatter.parse(dateStr);
		 
		//Java 8 datetime LocalDate
		LocalDate java8date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		java8date.format(DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMAT));
		System.out.println(java8date);
		
		LocalDate fromCustomPattern = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMAT));
		System.out.println(fromCustomPattern);
		System.out.println(java8date.format(DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMAT)));
	}

}
