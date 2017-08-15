package com.model.adapters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

// https://stackoverflow.com/a/13569598/1679643
public class DateAdapter  extends XmlAdapter<String, Date> {

	// modified for java 8 thread-safe date time. 
	private final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    @Override
    public String marshal(Date value) throws Exception {
    	LocalDateTime localDateTime = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    	return localDateTime.format(dateTimeFormatter);
    }

    @Override
    public Date unmarshal(String value) throws Exception {
        LocalDateTime ldt = LocalDateTime.parse(value, dateTimeFormatter);
        return (Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
    }

}
