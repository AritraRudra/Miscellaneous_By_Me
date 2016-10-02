package queue;

// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
// Each of the above basic operations must run at constant time O(1).
interface QueueInterface<T>{	

	/**
     * Add the item to the queue.
     */
	public void enqueue(T item);

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 *
	 * @return the item on this queue that was least recently added
	 * @throws NoSuchElementException
	 *             if this queue is empty
	 */
	public T dequeue();
	
	/**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
	public T peek();

	public boolean isEmpty();
	
	public int size();
	
	public void clear();
}