package using_spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import default_constructor_injection.DefaultConstructorInjection;
import parameterized_constructor_injection.BasicConstructorInjection;
import setter_injection.BasicSetterInjection;

/**
 * Main starting class
 *
 */
public class App {
	/** Name of this class */
	private static final String className = App.class.getName();
	/** Logger for this class */
	private static final Logger logger = LoggerFactory.getLogger(className);
	

	private String message = null;

	/**
	 * Main method.
	 */
	public static void main(String[] args) {
		
		logger.info("Initializing Spring context.");
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
		
		logger.info("Spring context initialized.");
		
		// Get Bean with id = main_starting_class
		App messageFromAppContext = (App) applicationContext.getBean("main");
		messageFromAppContext.printMessage();
		logger.info("Spring Application Context Message = "+messageFromAppContext.getMessage());
		
		
		
		// Get Bean with id = ID_DefaultConstructorInjection
		DefaultConstructorInjection defaultConstructorInjection 
			= ( DefaultConstructorInjection) applicationContext.getBean("ID_DefaultConstructorInjection");
		logger.info(defaultConstructorInjection.getMessage());
		
		// Get Bean with id = ID_BasicConstructorInjection
		BasicConstructorInjection basicConstructorInjection = (BasicConstructorInjection) applicationContext.getBean("ID_BasicConstructorInjection");
		logger.info(basicConstructorInjection.getMessage());
		
		// Get Bean with id = ID_BasicSetterInjection
		BasicSetterInjection basicSetterInjection = (BasicSetterInjection) applicationContext.getBean("ID_BasicSetterInjection");
		logger.info(basicSetterInjection.getMessage());
		
		// Get Bean with id = ID_BasicSetterInjectionXMLRefefence
		basicSetterInjection = (BasicSetterInjection) applicationContext.getBean("ID_BasicSetterInjectionXMLReference");
		logger.info(basicSetterInjection.getMessage());
		
		((ClassPathXmlApplicationContext)applicationContext).close();
	}


	private void printMessage() {
		logger.info("Fetched data from application-context.xml to App class.");
	}

	/**
	 * Gets message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}