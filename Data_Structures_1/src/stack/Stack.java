package stack;

public class Stack<T> {
	private T stack[];
	private int tos;
	private int maxSize;
	private int numOfItems;

	/**
	 * Constructor which initializes the stack with the mentioned size.
	 * 
	 * @param stackSize
	 */
	public Stack(int stackSize) {
		this.maxSize = stackSize;
		this.stack = (T[]) new Object[this.maxSize];
		this.tos = -1;
	}

	/**
	 * Push an item onto the stack
	 * 
	 * @param item
	 *            element to be pushed.
	 * @throws Exception
	 *             when Stack is full.
	 */
	public void push(T item) throws Exception {
		if (this.tos == (this.maxSize - 1))
			throw new Exception("Stack is full.");
		else {
			this.stack[++this.tos] = item;
			this.numOfItems++;
		}
	}

	/**
	 * Pop an item from the stack.
	 * 
	 * @return Returns the item present at top of the Stack.
	 * @throws Exception
	 *             when Stack is empty.
	 */
	public T pop() throws Exception {
		if (this.tos < 0)
			throw new Exception("Stack underflow.");
		else {
			this.numOfItems--;
			return this.stack[this.tos--];
		}
	}

	/**
	 * See the item on top of the stack. Just returns the item, does not changes Stack pointer 
	 * 
	 * @return Returns the item present at top of the Stack.
	 * @throws Exception
	 *             when Stack is empty.
	 */
	public T peek() throws Exception {
		if (this.tos < 0)
			throw new Exception("Stack underflow.");
		else {
			return this.stack[this.tos];
		}
	}
	/**
	 * Returns true if this Stack is full.
	 *
	 * @return {@code true} if this Stack is full; {@code false} otherwise.
	 */
	public boolean isFull() {
		return (this.tos == (this.maxSize - 1));
	}

	/**
	 * Returns true if this Stack is empty.
	 *
	 * @return {@code true} if this Stack is empty; {@code false} otherwise.
	 */
	public boolean isEmpty() {
		return (this.tos == -1);
	}
	
}