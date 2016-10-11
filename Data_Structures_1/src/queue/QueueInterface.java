package queue;

// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
// Each of the above basic operations must run at constant time O(1).
interface QueueInterface<T>{

	/**
     * Add the item to the queue.
     * @throws Exception
	 *             if this queue is full.
     */
	public void enqueue(T item) throws Exception;

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 *
	 * @return the item on this queue that was least recently added
	 * @throws Exception
	 *             if this queue is empty.
	 */
	public T dequeue() throws Exception;
	
	/**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue.
     * @throws Exception if this queue is empty.
     */
	public T peek() throws Exception;

	/**
	 * Returns true if this queue is full.
	 *
	 * @return {@code true} if this queue is full; {@code false} otherwise.
	 */
	public boolean isFull();

	/**
	 * Returns true if this queue is empty.
	 *
	 * @return {@code true} if this queue is empty; {@code false} otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of items in this queue.
	 *
	 * @return the number of items in this queue.
	 */
	public int getNumberOfItems();
	
	public void clear();
}