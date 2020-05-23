package stack;

public class StackEmptyException extends Exception{
	
	public StackEmptyException(){
		super();
	}
	
	public StackEmptyException(final String message){
		super(message);
	}
}