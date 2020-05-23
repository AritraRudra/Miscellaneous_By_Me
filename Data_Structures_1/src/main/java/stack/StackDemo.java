package stack;

public class StackDemo {
	public static void main(String[] args) {
		final int stackSize = 5;
		Stack<String> stack = new Stack<String>(stackSize);

		try {
			stack.push("A");
			System.out.println("Item pushed into stack : " + stack.peek());
			stack.push("B");
			System.out.println("Item pushed into stack : " + stack.peek());
			stack.push("C");
			System.out.println("Item pushed into stack : " + stack.peek());
			stack.push("D");
			System.out.println("Item pushed into stack : " + stack.peek());
			stack.push("E");
			System.out.println("Item pushed into stack : " + stack.peek());
			stack.push("F"); // Will throw StackFullException for stack size 5
			System.out.println("Item pusher into stack : " + stack.peek());
		} catch (StackFullException stkFullEx) {
			System.out.println(stkFullEx.getMessage());
		} catch (StackEmptyException stkEmptyEx) {
			System.out.println(stkEmptyEx.getMessage());
		}

		try {
			while (!stack.isEmpty()) {
				System.out.println(stack.peek());
				System.out.println("Item  popped out of the stack : " + stack.pop());
			}
			System.out.println("Item present at the top of stack : " + stack.peek());
		} catch (StackEmptyException stkEmptyEx) {
			System.out.println(stkEmptyEx.getMessage());
		}
	}
}