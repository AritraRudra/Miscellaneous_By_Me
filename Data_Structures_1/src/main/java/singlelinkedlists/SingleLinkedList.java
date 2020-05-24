package singlelinkedlists;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class SingleLinkedList<T> {
	Node<T> head;

	/** Utility fields */
	private int size = 0;

	public SingleLinkedList() {
	}

	public SingleLinkedList(final Node<T> head) {
		this.head = head;
	}

	public SingleLinkedList(final List<T> list) {
		if (list != null && !list.isEmpty()) {
			head = new Node<>(list.get(0));
			size = 1;
			if (list.size() == 1)
				return;
			final Iterator<T> itr = list.iterator();
			itr.next();
			Node<T> curr = head;
			Node<T> node = null;
			while (itr.hasNext()) {
				node = new Node<>(itr.next());
				node.next = null;
				curr.next = node;
				curr = node;
				size++;
			}
		}
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
		if (head == null) {
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
		if (head == null) {
			head = node;
			size++;
			return true;
		}
		node.next = head;
		head = node;
		size++;
		return true;
	}

	public boolean removeFromBeginning() {
		if (isEmpty())
			return false;
		@SuppressWarnings("unused")
		Node<T> temp = head;
		head = head.next;
		size--;
		temp = null; // Free to make it eligible for GC
		return true;
	}

	/**
	 * Removes the last element
	 *
	 * @return result of the operation.
	 */
	public boolean remove() {
		if (isEmpty())
			return false;
		// If only one element is present
		if (head.next == null) {
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

	private Node<T> getLastNode() {
		if (head == null)
			return head;
		Node<T> last = head;
		while (last.next != null)
			last = last.next;
		return last;
	}

	/** Utility methods */
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		if (head == null)
			return true;
		return false;
	}

	@Override
	public String toString() {
		if (head == null)
			return "";
		while (head.next == null)
			return head.val.toString();

		final StringBuilder sb = new StringBuilder();
		Node<T> curr = head;
		while (curr != null) {
			sb.append(curr.val + ", ");
			curr = curr.next;
		}
		// https://stackoverflow.com/a/3395329/1679643
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	public Node<T> getMinNode(final Comparator<? super T> comp) {
		if (comp == null)
			throw new InvalidParameterException("Comparater should not be null");
		return getMaxNode(comp.reversed());
	}

	public Node<T> getMaxNode(final Comparator<? super T> comp) {
		if (comp == null)
			throw new InvalidParameterException("Comparater should not be null");
		if (head == null || head.next == null)
			return head;

		Node<T> max = head;
		Node<T> curr = head;

		while (curr != null) {
			if (comp.compare(curr.val, max.val) > 0)
				max = curr;
			curr = curr.next;
		}
		return max;
	}

	public boolean contains(final Object o) {
		if (isEmpty())
			return false;
		Node<T> curr = head;
		while (curr != null) {
			if (Objects.equals(curr.val, o))
				return true;
			curr = curr.next;
		}
		return false;
	}

	public boolean remove(final Object o) {
		if (isEmpty())
			return false;
		Node<T> curr = head;
		boolean itemFound = false;
		while (curr.next != null) {
			if (Objects.equals(curr.next.val, o)) {
				itemFound = true;
				break;
			}
			curr = curr.next;
		}
		if (itemFound) {
			curr.next = curr.next.next;
			size--;
		}
		return false;
	}

	public void clear() {
		head = null;
		size = 0;
	}

	public T get(final int index) {
		if (isEmpty() || index >= size)
			throw new IndexOutOfBoundsException("Supplied index is " + index + ", index should not be < 0 || >= size()");
		int idx = 0;
		Node<T> curr = head;
		while (idx++ < index) {
			curr = curr.next;
		}
		return curr.val;
	}

	public T set(final int index, final T element) {
		if (isEmpty() || index >= size)
			throw new IndexOutOfBoundsException("Supplied index is " + index + ", index should not be < 0 || >= size()");
		Node<T> curr = head;
		T prevVal = null;
		int idx = 0;
		while (idx++ < index) {
			curr = curr.next;
		}
		prevVal = curr.val;
		curr.val = element;
		return prevVal;
	}

	public void add(final int index, final T element) {
		if (isEmpty() || index >= size)
			throw new IndexOutOfBoundsException("Supplied index is " + index + ", index should not be < 0 || >= size()");
		final Node<T> newNode = new Node<>(element);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
			size++;
			return;
		}

		int idx = 1;
		Node<T> curr = head;
		while (idx++ < index) {
			curr = curr.next;
		}
		newNode.next = curr.next;
		curr.next = newNode;
		size++;
	}

	public T remove(final int index) {
		if (isEmpty() || index >= size)
			throw new IndexOutOfBoundsException("Supplied index is " + index + ", index should not be < 0 || >= size()");
		Node<T> itemNode = head;
		if (index == 0) {
			itemNode = head;
			head = head.next;
			size--;
			return itemNode.val;
		}
		int idx = 1;
		Node<T> curr = head;
		while (idx++ < index)
			curr = curr.next;

		itemNode = curr.next;
		curr.next = curr.next.next;
		size--;
		return itemNode.val;
	}

	public int indexOf(final Object o) {
		int idx = 0;
		Node<T> curr = head;
		while (curr != null) {
			if (Objects.equals(curr.val, o))
				break;
			curr = curr.next;
			idx++;
		}
		return size == idx ? -1 : idx;
	}

	public int lastIndexOf(final Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void reverse() {
		Node<T> curr = head;
		Node<T> prev = null;
		Node<T> next = null;

		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;
	}

}
