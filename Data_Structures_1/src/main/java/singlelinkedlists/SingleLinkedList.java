package singlelinkedlists;

import java.util.Comparator;

public class SingleLinkedList<T> {
	Node<T> head;

	/** Utility fields */
	private int size = 0;

	public SingleLinkedList() {
	}

	public SingleLinkedList(final Node<T> head) {
		this.head = head;
	}

	public boolean add(final T val) {
		Node<T> node = null;
		try {
			node = new Node<>(val, null);
		} catch (final Exception e) {
			// Log
			return false;
		}
		// If this is the first element to add
		if(head == null) {
			head = node;
			size++;
			return true;
		}
		
		final Node<T> currLast = getLastNode();
		currLast.next = node;
		size++;
		return true;
	}
	
	public boolean addAtBeginning(final T val) {
		Node<T> node = null;
		try {
			node = new Node<>(val);
		} catch (final Exception e) {
			// Log
			return false;
		}
		// If this is the first element to add
		if(head == null) {
			head = node;
			size++;
			return true;
		}
		node.next =  head;
		head = node;
		size++;
		return true;
	}
	
	public boolean removeFromBeginning() {
		if(isEmpty())
			return false;
		Node<T> temp = head;
		head = head.next;
		size--;
		temp = null;	// Free to make it eligible for GC
		return true;
	}
	
	/**
	 * Removes the last element
	 * @return result of the operation.
	 */
	public boolean remove() {
		if(isEmpty())
			return false;
		// If only one element is present
		if(head.next == null) {
			head = null;
			size--;
			return true;
		}
			
		Node<T> curr = head.next;
		Node<T> prev = head;
		while (curr.next != null) {
			curr = curr.next;
			prev = prev.next;
		}
		prev.next = null;
		curr = null;
		size--;
		return true;
	}
	
	private Node<T> getLastNode(){
		if(head == null)
			return head;
		Node<T> last = head;
		while(last.next != null)
			last = last.next;
		return last;
	}

	/** Utility methods */
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(size);
		while(head.next != null)
			sb.append(head.val + ",");
		sb.deleteCharAt(size);
		return sb.toString();		
	}

	// TODO
	public Node<T> getMinNode(final Comparator<T> comp) {
		return head;
	}

	// TODO
	public Node<T> getMaxNode(final Comparator<T> comp) {
		return head;
	}
}
