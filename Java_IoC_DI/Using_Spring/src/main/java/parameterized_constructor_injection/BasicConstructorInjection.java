package parameterized_constructor_injection;

public class BasicConstructorInjection {
	private String message = null;

	/**
	 * Constructor
	 */
	public BasicConstructorInjection(String message) {
		this.message = message;
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