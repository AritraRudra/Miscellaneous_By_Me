package using_spring;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import setter_injection.BasicSetterInjection;

/**
 * Unit test for App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Configuration
@ContextConfiguration(locations = {"/SetterInjectionTest-context.xml"})
public class AppTest extends TestCase {
	final Logger logger = LoggerFactory.getLogger(AppTest.class);

	@Autowired
	private BasicSetterInjection message = null;

	/**
	 * Tests message.
	 */
	@org.junit.Test
	public void testMessage() {
		assertNotNull("Constructor message instance is null.", message);

		String msg = message.getMessage();

		assertNotNull("Message is null.", msg);

		String expectedMessage = "AppTest Setter Injection.";

		assertEquals("Message should be '" + expectedMessage + "'.", expectedMessage, msg);

		logger.info("message='{}'", msg);
	}
}