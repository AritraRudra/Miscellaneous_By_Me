import java.io.Serializable;

public class MessageBean implements Serializable{
	private static final long serialVersionUID = 5372195226763498453L;

	private String receiverName;
	private String senderName;
	private String textMessage;
	
	
	public MessageBean(String senderName, String textMessage) {
		this.receiverName = "";
		this.senderName = senderName;
		this.textMessage = textMessage;
	}

	public MessageBean(String receiverName, String senderName, String textMessage) {
		this.receiverName = receiverName;
		this.senderName = senderName;
		this.textMessage = textMessage;
	}

	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
}