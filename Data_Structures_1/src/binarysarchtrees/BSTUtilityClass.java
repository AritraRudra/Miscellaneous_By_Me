package binarysarchtrees;
/**
 * Utility class for BST.
 * Difference between binary tree amd BST => http://stackoverflow.com/questions/6380231/difference-between-binary-tree-and-binary-search-tree
 * 
 * @author Aritra
 * @param <T>
 */
public class BSTUtilityClass<T> {
	/**
	 * A function that constructs Balanced Binary Search Tree from a sorted
	 * array.
	 * 
	 * @param array
	 *            - The sorted array from which the BST needs to be constructed,
	 *            the array must be already sorted in increasing order.
	 * @param start
	 *            - The starting index of the array.
	 * @param end
	 *            - The ending index of the array.
	 * @return Node - Root node of the resultant BST.
	 */
	public Node<T> sortedArrayToBST(T array[], int start, int end) {

		/* Base Case */
		if (start > end) {
			return null;
		}

		/* Get the middle element and make it root */
		// same as (start+end)/2, avoids overflow.
		int mid = start + (end - start) / 2;

		Node<T> node = new Node<T>(array[mid]);

		/*
		 * Recursively construct the left subtree and make it left child of root
		 */
		node.leftChild = sortedArrayToBST(array, start, mid - 1);

		/*
		 * Recursively construct the right subtree and make it right child of root
		 */
		node.rightChild = sortedArrayToBST(array, mid + 1, end);

		return node;
	}

	
	/**
	 * An utility function to print pre-order traversal of BST to default output.
	 * 
	 * @param node
	 *            - The root node to traverse from.
	 */
	void printPreOrder(Node<T> node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		printPreOrder(node.leftChild);
		printPreOrder(node.rightChild);
	}
	
	/**
	 * An utility function to print in-order traversal of BST to default output.
	 * 
	 * @param node
	 *            - The root node to traverse from.
	 */
	void printInOrder(Node<T> node) {
		if (node == null) {
			return;
		}
		printInOrder(node.leftChild);
		System.out.print(node.data + " ");
		printInOrder(node.rightChild);
	}
	
	/**
	 * An utility function to print post-order traversal of BST to default
	 * output.
	 * 
	 * @param node
	 *            - The root node to traverse from.
	 */
	void printPostOrder(Node<T> node) {
		if (node == null) {
			return;
		}
		printPreOrder(node.leftChild);
		printPreOrder(node.rightChild);
		System.out.print(node.data + " ");
	}
	
}