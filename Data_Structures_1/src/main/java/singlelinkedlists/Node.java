package singlelinkedlists;

public class Node<T> {
	T val;
	Node<T> next;

	public Node() {}

	public Node(final T val) {
		this.val = val;
	}

	public Node(final T val, final Node<T> next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		return val.toString();
	}

}
