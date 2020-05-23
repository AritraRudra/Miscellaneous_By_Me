package queue;

public final class CircularQueue<T> implements QueueInterface<T>{
	private int front;
	private int rear;
	private int numOfItems;
	private int maxSize;
	private T[] items;
	
	/**
	 * Constructor which initializes the queue with the mentioned size.
	 * 
	 * @param queueSize
	 */
	public CircularQueue(final int queueSize) {
		super();
		this.maxSize = queueSize;
		this.front = -1;
		this.rear = -1;
		this.numOfItems = 0;
	}

	/**
     * Add the item to the queue.
     * @throws Exception
	 *             if this queue is full.
     */
	@Override
	public void enqueue(final T itemToAdd) throws Exception {
		if (isFull())
			throw new Exception("Queue is full");

		if (this.rear == (this.maxSize - 1))
			this.rear = 0;
		else
			this.rear++; // Since rear starts from -1 first time

		this.items[this.rear] = itemToAdd;
		this.numOfItems++;

		if (this.front == -1)
			this.front = 0;
	}

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 *
	 * @return the item on this queue that was least recently added.
	 * @throws Exception
	 *             if this queue is empty.
	 */
	@Override
	public T dequeue() throws Exception {
		if (isEmpty())
			throw new Exception("Queue is empty.");

		T itemDeleted = this.items[this.front];
		this.numOfItems--;

		if (this.front == this.rear)
			this.front = this.rear = -1;
		else {
			if (this.front == (this.maxSize - 1))	// Since front starts from -1, it will not cross ( MAXSIZE -1 )
				front = 0;
			else
				this.front++;
		}
		return itemDeleted;
	}

	/**
	 * Returns the item least recently added to this queue.
	 *
	 * @return the item least recently added to this queue.
	 * @throws Exception
	 *             if this queue is empty.
	 */
	@Override
	public T peek() throws Exception {
		if (isEmpty())
			throw new Exception("Queue underflow");
		return this.items[this.front];
	}

	/**
	 * Returns true if this queue is full.
	 *
	 * @return {@code true} if this queue is full; {@code false} otherwise.
	 */
	@Override
	public boolean isFull() {
		if ((this.front == 0 && this.rear == (this.maxSize - 1))
				|| ((this.rear + 1) == this.front) )
			return true;

		return false;
	}
	
	/**
	 * Returns true if this queue is empty.
	 *
	 * @return {@code true} if this queue is empty; {@code false} otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return (this.front == -1 ? true : false);
	}

	/**
	 * Returns the number of items in this queue.
	 *
	 * @return the number of items in this queue.
	 */
	@Override
	public int getNumberOfItems() {
		return this.numOfItems;
	}
	
	/**
	 * Returns a string representation of this queue.
	 *
	 * @return the sequence of items in FIFO order, separated by spaces.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T item : this.items)
			sb.append(item + " ");
		return sb.toString();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}