package datetime;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DateTimeStringConversionTest {
	private DateTimeStringConversion objectUnderTest;
	private Date date;
	private String expectedString;
	private String actualString;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		date = new Date();
		expectedString = DateTimeStringConversion.convertFromUtilDateToString(date,
				DateTimeStringConversion.DEFAULT_DATE_TIME_FORMAT);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testConvertFromUtilDateToStringPriorToJava8() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testConvertFromUtilDateToStringForNull() throws Exception{
		actualString = objectUnderTest.convertFromUtilDateToString(date, null);
		assertEquals(expectedString, actualString);
	}
	
	@Test
	public final void testConvertFromUtilDateToString() throws Exception{
		actualString = objectUnderTest.convertFromUtilDateToString(date,
				DateTimeStringConversion.DEFAULT_DATE_TIME_FORMAT);
		assertEquals(expectedString, actualString);
	}

	@Test
	public final void testGetLocalDateFromUtilDate() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetLocalDateTimeFromUtilDate() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testParseUtilDateFromStringPriorToJava8() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testParseUtilDateFromString() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testParseLocalDateFromString() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testParseLocalDateTimeFromString() {
		// fail("Not yet implemented"); // TODO
	}

}
