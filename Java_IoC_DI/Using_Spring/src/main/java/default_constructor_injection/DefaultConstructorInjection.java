package default_constructor_injection;

public class DefaultConstructorInjection {
	// When the container instantiates the message bean, it is equivalent to
	// initializing an object in your code with 'new DefaultMessage()'.

	private String message = "Default Constructor Test Using Spring.";
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