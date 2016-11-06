package binarysearchtrees;

import java.util.Stack;
import java.util.Iterator;
import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> root;
	private Comparator<T> comparator;

	// ====================================================
	// 				Default Constructor
	// ====================================================
	public BinarySearchTree() {
		this.root = null;
		this.comparator = null;
	}

	// ====================================================
	// 				Parameterized Constructor
	// ====================================================
	/**
	 * Parameterized constructor through which a custom comparator can be
	 * specified.
	 * 
	 * @param comp
	 *            - The specified comparator.
	 */
	public BinarySearchTree(Comparator<T> comp) {
		this.root = null;
		this.comparator = comp;
	}

	private int compare(T x, T y) {
		// http://stackoverflow.com/questions/20052155/check-if-an-object-is-of-the-same-class-as-this
		// http://stackoverflow.com/a/20052297/1679643
		if(!(x.getClass() == y.getClass())){
			throw new RuntimeException("Incompatible class comparison.");
		}
		if (this.comparator == null){
			// Assuming x and y are ints
			return x.compareTo(y);
			
			/*
			 * TODO Implement/handle the comparator when not supplied externally.
			*/
			/*
			if(x instanceof Integer){
				
				return (y - x);
			}
			*/
		}
		else
			return comparator.compare(x, y);
	}
	
	/*	
	public int compare(Integer x, Integer y) {
		return y - x;
	}
	*/

	// ====================================================
	// 			Insert a node into the BST.
	// ====================================================
	/**
	 * Insert data in the BST.
	 * 
	 * @param data
	 *            -The data to insert.
	 */
	public void insert(T data) {
		this.root = this.insert(this.root, data);
	}

	private Node<T> insert(Node<T> node, T dataToInsert) {
		if (node == null)
			return new Node<T>(dataToInsert);

		if (this.compare(dataToInsert, node.data) == 0)
			return node;

		if (this.compare(dataToInsert, node.data) < 0)
			node.leftChild = this.insert(node.leftChild, dataToInsert);
		else
			node.rightChild = this.insert(node.rightChild, dataToInsert);

		return node;
	}

	//=========================================================
	// 				Search for a particular node.
	//=========================================================
	/**
	 * Check if the BST has any node with the specified data.
	 * 
	 * @param dataToSearch
	 *            - The data to search.
	 * @return {@code True} if the data is present in the tree, {@code False}
	 *         otherwise.
	 */
	public boolean search(T dataToSearch) {
		return this.search(this.root, dataToSearch);
	}

	private boolean search(Node<T> node, T dataToSearch) {
		if (node == null)
			return false;
		else if (this.compare(dataToSearch, node.data) == 0)
			return true;
		else {
			if (this.compare(dataToSearch, node.data) < 0)
				return this.search(node.leftChild, dataToSearch);
			else
				return this.search(node.rightChild, dataToSearch);
		}
	}

	//=========================================================
	// 			Delete the node having the specified data.
	//=========================================================
	/**
	 * Delete the node having the specified data.
	 * 
	 * @param dataToDelete
	 */
	public boolean delete(T dataToDelete) {
		if(this.root == null)
			throw new NullPointerException("Tree is not yet initialized ( root is null ).");
		
		Node<T> parent = root;
		Node<T> current = root;
		boolean isLeftChild = false;

		while (current.data != dataToDelete) {
			parent = current;
			if (this.compare(dataToDelete, current.data) > 0) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				return false;
			}
		}

		// if we are here that means we have found the node

		// Case 1: if node to be deleted has no children
		if (current.leftChild == null && current.rightChild == null) {
			if (current == this.root) {
				this.root = null;
			}
			if (isLeftChild == true) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		}

		// Case 2 : if node to be deleted has only one child
		else if (current.rightChild == null) {
			if (current == this.root) {
				this.root = current.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		} else if (current.leftChild == null) {
			if (current == this.root) {
				this.root = current.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		} else if (current.leftChild != null && current.rightChild != null) {

			// now we have found the minimum element in the right sub tree
			Node<T> successor = this.getSuccessorToDelete(current);
			if (current == this.root) {
				this.root = successor;
			} else if (isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}
		return true;
	}
	
	private Node<T> getSuccessorToDelete(Node<T> nodeToDelete){
		Node<T> successsor =null;
		Node<T> successsorParent =null;
		Node<T> current = nodeToDelete.rightChild;

		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.leftChild;
		}
		
		/*
		 * Check if successor has a right child, it cannot have left child for sure.
		 * If it does have the right child, add it to the left of successorParent.
		 */
		if(successsor!=nodeToDelete.rightChild){
			successsorParent.leftChild = successsor.rightChild;
			successsor.rightChild = nodeToDelete.rightChild;
		}
		return successsor;
	}


	// From https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
	// More concise than the other delete method.
	/*
	public void delete(T dataToDelete) {
		this.root = this.delete(this.root, dataToDelete);
	}
	*/
	
	@SuppressWarnings("unused")
	private Node<T> delete(Node<T> node, T dataToDelete) {
		if (node == null)
			throw new NullPointerException("Tree is not yet initialized ( root is null ).");
		else if (this.compare(dataToDelete, node.data) < 0)
			node.leftChild = this.delete(node.leftChild, dataToDelete);
		else if (this.compare(dataToDelete, node.data) > 0)
			node.rightChild = this.delete(node.rightChild, dataToDelete);
		else {
			if (node.leftChild == null)
				return node.rightChild;
			else if (node.rightChild == null)
				return node.leftChild;
			else {
				// get data from the rightmost node in the left subtree
				node.data = this.retrieveData(node.leftChild);
				// delete the rightmost node in the left subtree
				node.leftChild = this.delete(node.leftChild, node.data);
			}
		}
		return node;
	}

	private T retrieveData(Node<T> node) {
		while (node.rightChild != null)
			node = node.rightChild;

		return node.data;
	}

	/**
	 * This method checks if binary tree is empty or not.
	 * Time Complexity of this solution is constant O(1)
	 * for best, average and worst case.
	 * 
	 * @return True if binary search tree is empty.
	 */
	public boolean isEmpty() {
		return (null == this.root);
	}


	/**
	 * Get the canonically maximum value present in the BST.
	 *  
	 * @return The maximum canonical!! value.
	 */
	// TODO Implement getMax
	public T getMax(){
		if(this.root == null)
			throw new NullPointerException("Tree is not yet initialized ( root is null ).");

		return this.root.data;
	}
	
	/**
	 * Get the canonically minimunm value present in the BST.
	 *  
	 * @return The mimimum canonical!! value.
	 */
	// TODO Implement getMax
	public T getMin(){
		if(this.root == null)
			throw new NullPointerException("Tree is not yet initialized ( root is null ).");

		return this.root.data;
	}
		
	
	//=========================================================
	// 					toString
	//=========================================================
	/*
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (T data : this)	// ???
			sb.append(data.toString());

		return sb.toString();
	}
	*/

	//=========================================================
	// 				Some useful methods
	//=========================================================
	/**
	 * Get the height of the BST.
	 * 
	 * @return Height of the tree with node at height zero (0).
	 */
	public int height() {
		return height(this.root);
	}

	private int height(Node<T> node) {
		if (node == null)
			return -1;
		else
			return (1 + Math.max(this.height(node.leftChild), this.height(node.rightChild)));
	}

	/**
	 * Get the number of leaf nodes in the BST.
	 * 
	 * @return The number of leaf nodes in the BST.
	 */
	public int countLeaves() {
		return countLeaves(this.root);
	}

	private int countLeaves(Node<T> node) {
		if (node == null)
			return 0;
		else if (node.leftChild == null && node.rightChild == null)
			return 1;
		else
			return (this.countLeaves(node.leftChild) + this.countLeaves(node.rightChild));
	}
	
	/**
	 * Returns the number of nodes in this binary search tree.
	 * Time complexity of this method is O(n).
	 * 
	 * @return Size of this binary search tree.
	 */
	public int size() {
		int size = 0;
		Node<T> current = root;
		Stack<Node<T>> stack = new Stack<Node<T>>();

		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.leftChild;
			} else {
				size++;
				current = stack.pop();
				current = current.rightChild;
			}
		}
		return size;
	}


	@Override
	public Iterator<T> iterator() {
		return new BSTIterator();
	}

	// http://stackoverflow.com/a/17915120/1679643
	// pre-order
	private class BSTIterator implements Iterator<T> {
		Stack<Node<T>> stk = new Stack<Node<T>>();

		public BSTIterator() {
			if (root != null)
				stk.push(root);
		}

		public boolean hasNext() {
			return !(stk.isEmpty());
		}

		public T next() {
			Node<T> currentNode = stk.peek();
			if (currentNode.leftChild != null) {
				stk.push(currentNode.leftChild);
			} else {
				Node<T> tmpNode = stk.pop();
				while (tmpNode.rightChild == null) {
					if (stk.isEmpty())
						return currentNode.data;
					tmpNode = stk.pop();
				}
				stk.push(tmpNode.rightChild);
			}
			return currentNode.data;
		}
	}//End of BSTIterator

}