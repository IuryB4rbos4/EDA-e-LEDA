package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.heightRecursive(root);
	}

	private int heightRecursive(BSTNode<T> nodeCurrent) {
		int result = -1;

		if (!nodeCurrent.isEmpty()) {
			result = 1 + Math.max(
					this.heightRecursive((BSTNode<T>) nodeCurrent.getLeft()),
					this.heightRecursive((BSTNode<T>) nodeCurrent.getRight()));
		}

		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<T>();
		if (element != null) {
			result = searchRecursive(this.root, element);
		}

		return result;
	}

	private BSTNode<T> searchRecursive(BSTNode<T> nodeCurrent, T element) {
		BSTNode<T> result = nodeCurrent;

		if (nodeCurrent.isEmpty() || nodeCurrent.getData().equals(element)) {
			result = nodeCurrent;
		} else if (element.compareTo(nodeCurrent.getData()) > 0) {
			result = this.searchRecursive((BSTNode<T>) nodeCurrent.getRight(), element);
		} else {
			result = this.searchRecursive((BSTNode<T>) nodeCurrent.getLeft(), element);
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insertRecursive(this.root, element);
		}
	}

	private void insertRecursive(BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent.isEmpty()) {
			nodeCurrent.setData(element);
			nodeCurrent.setRight(new BSTNode.Builder<T>().parent(nodeCurrent).build());
			nodeCurrent.setLeft(new BSTNode.Builder<T>().parent(nodeCurrent).build());
		} else {
			if (element.compareTo(nodeCurrent.getData()) > 0)
				this.insertRecursive((BSTNode<T>) nodeCurrent.getRight(), element);
			else
				this.insertRecursive((BSTNode<T>) nodeCurrent.getLeft(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> max = null;
		if (!this.isEmpty()) {
			max = this.maximumRecursive(this.root);
		}

		return max;
	}

	private BSTNode<T> maximumRecursive(BSTNode<T> nodeCurrent) {
		if (!nodeCurrent.getRight().isEmpty()) {
			nodeCurrent = this.maximumRecursive((BSTNode<T>) nodeCurrent.getRight());
		} 
		return nodeCurrent;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> min = null;
		if (!this.isEmpty()) {
			min = this.minimumRecursive(this.root);
		}
		return min;
	}

	private BSTNode<T> minimumRecursive(BSTNode<T> nodeCurrent) {
		if (!nodeCurrent.getLeft().isEmpty()) {
			nodeCurrent = this.minimumRecursive((BSTNode<T>) nodeCurrent.getLeft());
		} 
		return nodeCurrent;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> nodeCurrent = this.search(element);

		if (!nodeCurrent.isEmpty()) {
			if (!nodeCurrent.getRight().isEmpty()) {
				nodeCurrent = this.minimumRecursive((BSTNode<T>) nodeCurrent.getRight());

			} else {
				nodeCurrent = this.sucessorRecursive(nodeCurrent, element);
			}
		}

		return (nodeCurrent == null || nodeCurrent.isEmpty()) ? null : nodeCurrent;
	}

	private BSTNode<T> sucessorRecursive(BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent != null && nodeCurrent.getData().compareTo(element) <= 0) {
			nodeCurrent = this.sucessorRecursive((BSTNode<T>) nodeCurrent.getParent(), element);
		} 
		return nodeCurrent;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> nodeCurrent = this.search(element);

		if (!nodeCurrent.isEmpty()) {
			if (!nodeCurrent.getLeft().isEmpty()) {
				nodeCurrent = this.maximumRecursive((BSTNode<T>) nodeCurrent.getLeft());
			} else {
				nodeCurrent = this.predecessorRecursive(nodeCurrent, element);
			}
		}

		return (nodeCurrent == null || nodeCurrent.isEmpty()) ? null : nodeCurrent;
	}

	private BSTNode<T> predecessorRecursive(BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent != null && nodeCurrent.getData().compareTo(element) >= 0) {
			nodeCurrent = this.predecessorRecursive((BSTNode<T>) nodeCurrent.getParent(), element);
		}
		return nodeCurrent;
	}

	@Override
	public void remove(T element) {
		if(element != null){
			BSTNode<T> nodeCurrent = this.search(element);

			if(!nodeCurrent.isEmpty()){
				if(nodeCurrent.isLeaf()){
					removeLeaf(nodeCurrent);
				} else if(nodeCurrent.getLeft().isEmpty() || nodeCurrent.getRight().isEmpty()){
					removeOneLeaf(nodeCurrent);
				} else {
					removeTwoLeaf(nodeCurrent);
				}
			}
		}
	}

	private void removeTwoLeaf(BSTNode<T> nodeCurrent) {
		T sucessor = this.sucessor(nodeCurrent.getData()).getData();
		this.remove(sucessor);
		nodeCurrent.setData(sucessor);
	}

	private void removeOneLeaf(BSTNode<T> nodeCurrent) {
		BSTNode<T> childNode = nodeCurrent.getRight().isEmpty() ? (BSTNode<T>) nodeCurrent.getLeft() : (BSTNode<T>) nodeCurrent.getRight();
		if(getRoot().equals(nodeCurrent)){
			this.root = childNode;
			this.root.setParent(null);
		} else {
			childNode.setParent(nodeCurrent.getParent());
			if(nodeCurrent.getParent().getRight().equals(nodeCurrent)){
				nodeCurrent.getParent().setRight(childNode);
			} else {
				nodeCurrent.getParent().setLeft(childNode);
			}
		}
	}

	private void removeLeaf(BSTNode<T> nodeCurrent) {
		nodeCurrent.setData(null);
		nodeCurrent.setLeft(null);
		nodeCurrent.setRight(null);
	}

	@Override
	public T[] preOrder() {
		return preOrderRecursive(this.root, new ArrayList<>());
	}

	private T[] preOrderRecursive(BSTNode<T> nodeCurrent, ArrayList<T> list) {
		if (!nodeCurrent.isEmpty()) {
			list.add(nodeCurrent.getData());
			this.preOrderRecursive((BSTNode<T>) nodeCurrent.getLeft(), list);
			this.preOrderRecursive((BSTNode<T>) nodeCurrent.getRight(), list);
		}
		return (T[]) list.toArray(new Comparable[0]);
	}

	@Override
	public T[] order() {
		return orderRecursive(this.root, new ArrayList<>());
	}

	private T[] orderRecursive(BSTNode<T> nodeCurrent, ArrayList<T> list) {
		if (!nodeCurrent.isEmpty()) {
			this.orderRecursive((BSTNode<T>) nodeCurrent.getLeft(), list);
			list.add(nodeCurrent.getData());
			this.orderRecursive((BSTNode<T>) nodeCurrent.getRight(), list);
		}
		return (T[]) list.toArray(new Comparable[0]);
	}

	@Override
	public T[] postOrder() {
		return posOrderRecursive(this.root, new ArrayList<>());
	}

	private T[] posOrderRecursive(BSTNode<T> nodeCurrent, ArrayList list) {
		if (!nodeCurrent.isEmpty()) {
			this.posOrderRecursive((BSTNode<T>) nodeCurrent.getLeft(), list);
			this.posOrderRecursive((BSTNode<T>) nodeCurrent.getRight(), list);
			list.add(nodeCurrent.getData());
		}
		return (T[]) list.toArray(new Comparable[0]);
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
