package stack;

public class StackDemo {
	public static void main(String[] args) {
		final int stackSize = 5;
		Stack<String> stack = new Stack<String>(stackSize);
		
		try{
			stack.push("A");
			stack.push("B");
			stack.push("C");
			stack.push("D");
			stack.push("E");
			stack.push("F");	// Will through StackFullException for stack size 5
		}catch(StackFullException stkFullEx){
			System.out.println(stkFullEx.getMessage());
		}
		
		try{
			while(!stack.isEmpty()){
				System.out.println(stack.peek());
				System.out.println(stack.pop());
			}
		}catch(StackEmptyException stkEmptyEx){
			System.out.println(stkEmptyEx.getMessage());
		}
	}
}