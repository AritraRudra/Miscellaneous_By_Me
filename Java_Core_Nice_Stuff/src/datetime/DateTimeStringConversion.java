package datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeStringConversion {

	public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";
	public static final String DEFAULT_DATE_TIME_FORMAT = "dd-MMM-yyyy HH:mm:ss";
	public static final DateTimeFormatter DEFAULT_DATE_FORMATTER 
						= DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
	public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER 
						= DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);

	// Thread-safe
	public static String convertFromUtilDateToStringPriorToJava8(final Date dateToConvert,
			final String stringFormat) throws Exception {
		SimpleDateFormat dateTimeFormat;
		if (stringFormat != null)
			dateTimeFormat = new SimpleDateFormat(stringFormat);
		else
			dateTimeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

		synchronized (dateTimeFormat) {
			return dateTimeFormat.format(dateToConvert);
		}
	}

	// Using java 8 LocalDateTime which is thread-safe
	public static String convertFromUtilDateToString(final Date dateToConvert,
			final String stringFormat) throws Exception {
		final LocalDateTime localDateTime = dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		if (stringFormat != null)
			return localDateTime.format(DateTimeFormatter.ofPattern(stringFormat));
		else
			return localDateTime.format(DEFAULT_DATE_TIME_FORMATTER);
	}

	public static LocalDate getLocalDateFromUtilDate(final Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime getLocalDateTimeFromUtilDate(final Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	// Thread-safe
	public static Date parseUtilDateFromStringPriorToJava8(final String stringToParse,
			final String stringFormat) throws Exception {
		SimpleDateFormat dateTimeFormat;
		if (stringFormat == null)
			dateTimeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
		else
			dateTimeFormat = new SimpleDateFormat(stringFormat);

		synchronized (dateTimeFormat) {
			return dateTimeFormat.parse(stringToParse);
		}
	}

	public static Date parseUtilDateFromString(final String stringToParse,
			final String stringFormat) throws Exception {
		LocalDateTime ldt;
		if (stringFormat == null)
			ldt = LocalDateTime.parse(stringToParse, DEFAULT_DATE_TIME_FORMATTER);
		else
			ldt = LocalDateTime.parse(stringToParse, DateTimeFormatter.ofPattern(stringFormat));
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate parseLocalDateFromString(final String stringToParse,
			final String stringFormat) throws Exception {
		if (stringFormat == null)
			return LocalDate.parse(stringToParse, DEFAULT_DATE_FORMATTER);
		else
			return LocalDate.parse(stringToParse, DateTimeFormatter.ofPattern(stringFormat));
	}

	public static LocalDateTime parseLocalDateTimeFromString(final String stringToParse, 
			final String stringFormat) throws Exception {
		if (stringFormat == null)
			return LocalDateTime.parse(stringToParse, DEFAULT_DATE_TIME_FORMATTER);
		else
			return LocalDateTime.parse(stringToParse, DateTimeFormatter.ofPattern(stringFormat));
	}

}
