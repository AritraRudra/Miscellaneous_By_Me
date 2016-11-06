package binarysarchtrees;

/**
 * Class for a binary tree node
 * @author Aritra
 *
 * @param <T>
 */
public class Node<T> {
	protected T data;
	protected Node<T> leftChild;
	protected Node<T> rightChild;
	
	public Node(T data) {
		this.data = data;
		this.leftChild = this.rightChild = null;
	}
	
	public Node(T data, Node<T> left, Node<T> right) {
		this.leftChild = left;
		this.data = data;
		this.rightChild = right;
	}

	public String toString() {
		return this.data.toString();
	}
}