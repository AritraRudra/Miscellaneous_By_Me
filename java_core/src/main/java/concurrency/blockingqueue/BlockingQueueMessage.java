package concurrency.blockingqueue;

public class BlockingQueueMessage {

	private String message;

	public BlockingQueueMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}