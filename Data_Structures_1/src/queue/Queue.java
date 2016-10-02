package queue;

import java.util.ArrayList;

public class Queue<T> /* implements QueueInterface<T> */ {
	private int front;
	private int rear;
	private int numOfItems;
	private int maxSize;
	private T[] items;
	//private ArrayList<T> items;
	
	/**
	 * Constructor which initializes the queue with the mentioned size.
	 * 
	 * @param queueSize
	 */
	public Queue(int queueSize) {
		super();
		this.maxSize = queueSize;
		
		// http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
		// http://stackoverflow.com/questions/18581002/how-to-create-a-generic-array
		
		// Arrays are covariant, Generics are not.
		// You should not mix-up arrays and generics. They don't go well together.
		// There are differences in how arrays and generic types enforce the type check.
		// We say that arrays are reified, but generics are not
		items = (T[]) new Object[this.maxSize];

		// items=new ArrayList<T>(this.maxSize);
		this.front = 0;
		this.rear = -1;
		this.numOfItems = 0;
	}
	
	/**
	 * Returns a string representation of this queue.
	 *
	 * @return the sequence of items in FIFO order, separated by spaces
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T item : this.items)
			sb.append(item + " ");
		return sb.toString();
	}

	/**
	 * Returns the number of items in this queue.
	 *
	 * @return the number of items in this queue
	 */
	public int length() {
		return this.numOfItems;
	}

	/**
	 * Returns the number of items in this queue.
	 *
	 * @return the number of items in this queue
	 */
	public int size() {
		return this.numOfItems;
	}

	/**
	 * Add the item to the queue.
	 * @throws Exception 
	 */
	// @Override
	public void enqueue(T item) throws Exception {
		// Check for queue overflow
		if (this.rear == (this.maxSize - 1)){
			if(this.front > 0 ) // it means one or more item(s) has been deleted and there is space in front
				this.rear = -1;
			else
				throw new Exception("Queue is full");
		}
		this.items[++this.rear] = item;
		// TODO check and handle the ever increasing numOfItems
		this.numOfItems++;
	}

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 *
	 * @return the item on this queue that was least recently added
	 * @throws Exception
	 *             if this queue is empty
	 */
	// @Override
	public T dequeue() throws Exception {
		if (isEmpty())
			throw new Exception("Queue underflow");
		T itemDeleted = this.items[this.front++];
		if (this.front == this.maxSize)
			this.front = 0;
		this.numOfItems--;
		return itemDeleted;
	}

	/**
	 * Returns the item least recently added to this queue.
	 *
	 * @return the item least recently added to this queue
	 * @throws Exception
	 *             if this queue is empty
	 */
	// @Override
	public T peek() throws Exception {
		if (isEmpty())
			throw new Exception("Queue underflow");
		return this.items[this.front];
	}

	/**
	 * Returns true if this queue is empty.
	 *
	 * @return {@code true} if this queue is empty; {@code false} otherwise
	 */
	// @Override
	public boolean isEmpty() {
		return (this.numOfItems == 0);
	}

	/**
	 * Returns true if this queue is full.
	 *
	 * @return {@code true} if this queue is full; {@code false} otherwise
	 */
	// @Override
	public boolean isFull() {
		return (this.numOfItems == this.maxSize);
	}

	// @Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	private void resize(int newCapacity){
        T[] tmp = (T[]) new Object[newCapacity];

        for (int i = 0; i < this.numOfItems; i++)
            tmp[i] = this.items[(this.front + i) % this.items.length];

        this.items = tmp;
        this.front = 0;
        this.rear = this.numOfItems;
    }
}