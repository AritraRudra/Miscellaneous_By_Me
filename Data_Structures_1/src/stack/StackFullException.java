package stack;

public class StackFullException extends Exception{
	
	public StackFullException() {
		super();
	}
	
	public StackFullException(final String message){
		super(message);
	}
}